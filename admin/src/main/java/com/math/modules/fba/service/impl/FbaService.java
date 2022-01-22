package com.math.modules.fba.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.math.modules.account.entity.AccountEntity;
import com.math.modules.account.service.IAccountService;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.math.modules.arrival.service.IArrivalService;
import com.math.modules.fba.service.IFbaService;
import com.math.modules.fbaProduct.entity.FbaProductEntity;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.fbaStore.entity.FbaStoreEntity;
import com.math.modules.fbaStore.service.IFbaStoreService;
import com.math.modules.item.entity.ItemEntity;
import com.math.modules.item.service.IItemService;
import com.math.modules.listing.entity.ListingEntity;
import com.math.modules.listing.service.IListingService;
import com.math.modules.monday.entity.MondayEntity;
import com.math.modules.monday.service.IMondayService;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.sale.entity.SaleEntity;
import com.math.modules.sale.service.ISaleService;
import com.math.modules.saleSum.entity.SaleSumEntity;
import com.math.modules.saleSum.service.ISaleSumService;
import com.math.modules.seller.entity.SellerEntity;
import com.math.modules.seller.service.ISellerService;
import com.math.sdk.samples.OrderDemo;
import com.yph.enun.CountryEnum;
import com.yph.param.SystemParam;
import com.yph.util.DateUtil;
import com.yph.util.Map2JavaBeanUtil;
import com.yph.util.MapUtil;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FbaService implements IFbaService {


    @Autowired
    ISaleService saleService;

    @Autowired
    ISaleSumService saleSumService;

    @Autowired
    IMondayService mondayService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncSale() throws Exception {
        saleSumService.remove(new QueryWrapper<SaleSumEntity>().ge("sum_id", 1));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject js = OrderDemo.postForKing("/data/seller/lists", new HashMap<>());
        saleService.update(new UpdateWrapper<SaleEntity>().set("actual_sale", 0));
        for (Object data : js.getJSONArray("data")) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            int sid = Integer.parseInt(String.valueOf(dataMap.get("sid")));
            String country = (String) dataMap.get("country");
//            String country = "美国";
            Map<String, Object> body = new HashMap();
            body.put("offset", 0);
            body.put("length", Integer.MAX_VALUE);
            body.put("sid", sid);
            body.put("start_date", "2021-10-01");
            body.put("end_date", DateUtil.getNowDateString(1));

            JSONObject jsonObject = OrderDemo.postForKing("/data/sales_report/sales", body);
            JSONArray data1 = jsonObject.getJSONArray("data");
            String s = String.valueOf(CountryEnum.getRoot(country));
            for (Object datum : data1) {
                Map<String, Object> datum1 = (Map<String, Object>) datum;
                Object r_date = datum1.get("r_date");
                Object asin = datum1.get("asin");
                Object volume = datum1.get("volume");
                Object amount = datum1.get("amount");
                SaleEntity one = saleService.getOne(new QueryWrapper<SaleEntity>().eq("asin", asin).eq("times", simpleDateFormat.parse(String.valueOf(r_date))).eq("site", s));
                if (one != null) {
                    one.setActualSale(one.getActualSale().add(new BigDecimal(String.valueOf(volume))));
                    one.setAmount(new BigDecimal(String.valueOf(amount)));
                    saleService.updateById(one);
                }
                MapUtil.mapKeySetLine2Upper(datum1);
                SaleSumEntity saleSumEntity = new SaleSumEntity();
                Object rDate = datum1.get("rDate");
                Map2JavaBeanUtil.transMap2Bean(datum1, saleSumEntity);
                if (rDate != null) {
                    saleSumEntity.setRDate(simpleDateFormat.parse(String.valueOf(rDate)));
                }
                saleSumEntity.setSellerId(sid);
                saleSumService.save(saleSumEntity);
            }
        }
    }

    @Autowired
    private IFbaStoreService fbaStoreService;
    @Autowired
    private INowService nowService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncFbaStore() throws Exception {
        nowService.remove(new QueryWrapper<NowEntity>().ge("now_id", 1));
        fbaStoreService.remove(new QueryWrapper<FbaStoreEntity>().ge("fbain_id", 1));
        JSONObject js = OrderDemo.postForKing("/data/seller/lists", new HashMap<>());
        List<FbaStoreEntity> saveList = new LinkedList();
        for (Object data : js.getJSONArray("data")) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            int sid = Integer.parseInt(String.valueOf(dataMap.get("sid")));
            String country = (String) dataMap.get("country");
            Map<String, Object> body = new HashMap();
            body.put("offset", 0);
            body.put("length", Integer.MAX_VALUE);
            body.put("sid", sid);
            JSONObject jsonObject = OrderDemo.postForKing("/routing/fba/fbaStock/fbaList", body);
            String code = jsonObject.getString("code");

            if (!SystemParam.countrys.contains(country)) {
                continue;
            }
            if (!code.equals("0")) {
                continue;
//                return;
//            return R.error(jsonObject.getString("message"));
            }
            JSONObject data1 = jsonObject.getJSONObject("data");
            JSONArray list = data1.getJSONArray("list");
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                Map<String, Object> o1 = (Map<String, Object>) o;
                FbaStoreEntity fbaStoreEntity = new FbaStoreEntity();
                MapUtil.mapKeySetLine2Upper(o1);
                Map2JavaBeanUtil.transMap2Bean(o1, fbaStoreEntity);
                fbaStoreEntity.setSellerId(sid);

                saveList.add(fbaStoreEntity);


                NowEntity one = nowService.getOne(new QueryWrapper<NowEntity>().eq("asin", fbaStoreEntity.getAsin()).eq("site", country));

                if (one == null) {
                    NowEntity nowEntity = new NowEntity();
                    nowEntity.setAsin(fbaStoreEntity.getAsin());
                    nowEntity.setSite(country);
//                    nowEntity.setSellerId(sid);
                    //data.afnFulfillableQuantity+data.reservedFcTransfers+data.reservedFcProcessing
                    nowEntity.setNowCount(fbaStoreEntity.getAfnFulfillableQuantity() + fbaStoreEntity.getReservedFcProcessing() + fbaStoreEntity.getReservedFcTransfers());
                    nowService.save(nowEntity);
                } else {
                    UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper();
                    updateWrapper.setSql("now_count=now_count+" + (fbaStoreEntity.getAfnFulfillableQuantity() + fbaStoreEntity.getReservedFcProcessing() + fbaStoreEntity.getReservedFcTransfers()));
                    updateWrapper.eq("now_id", one.getNowId());
                    nowService.update(updateWrapper);
                }



                if (saveList.size() == 1000) {
                    fbaStoreService.saveBatch(saveList);
                    saveList.clear();
                }
            }
        }
        if (saveList.size() > 0) {
            fbaStoreService.saveBatch(saveList);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.MONDAY) {
            //周一
            for (NowEntity nowEntity : nowService.list()) {
//                nowEntity.setMondayCount(nowEntity.getNowCount());
//                nowService.updateById(nowEntity);
                MondayEntity mondayEntity = new MondayEntity();
                mondayEntity.setAsin(nowEntity.getAsin());
                mondayEntity.setCount(nowEntity.getNowCount());
                mondayEntity.setTimes(DateUtil.getNowDateDay());
                mondayEntity.setSite(nowEntity.getSite());
                mondayService.save(mondayEntity);
            }
        }



    }

    @Autowired
    IFbaProductService fbaProductService;
    @Autowired
    IItemService iItemService;

    @Autowired
    IArrivalService arrivalService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncFbaProduct() throws Exception {
        fbaProductService.remove(new QueryWrapper<FbaProductEntity>().ge("fba_product_id", 1));
        iItemService.remove(new QueryWrapper<ItemEntity>().ge("item_id", 1));
        JSONObject js = OrderDemo.postForKing("/data/seller/lists", new HashMap<>());
//        for (Object data : js.getJSONArray("data")) {
//            Map<String, Object> dataMap = (Map<String, Object>) data;
//            int sid = Integer.parseInt(String.valueOf(dataMap.get("sid")));

        Map<String, Object> body = new HashMap();
        body.put("offset", 0);
        body.put("length", Integer.MAX_VALUE);
        body.put("sid", 825);
        body.put("start_date", "2019-07-12");
        body.put("end_date", DateUtil.getNowDateString());
        JSONObject jsonObject = OrderDemo.postForKing("/data/fba_report/shipmentList", body);
        String code = jsonObject.getString("code");
        if (!code.equals("0")) {
//            return R.error(jsonObject.getString("message"));
        }
        JSONObject data1;
        try {
            data1 = jsonObject.getJSONObject("data");
        } catch (ClassCastException e) {
            return;
        }
        JSONArray list = data1.getJSONArray("list");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) list.get(i);
            MapUtil.mapKeySetLine2Upper(map);
            FbaProductEntity fbaProductEntity = new FbaProductEntity();
            Map2JavaBeanUtil.transMap2Bean(map, fbaProductEntity);
            fbaProductEntity.setSellerId(825);
            fbaProductService.save(fbaProductEntity);
            JSONObject jsonObject1 = list.getJSONObject(i);
            JSONArray item_list = jsonObject1.getJSONArray("itemList");
            for (int i1 = 0; i1 < item_list.size(); i1++) {
                Object o2 = item_list.get(i1);
                Map<String, Object> o21 = (Map<String, Object>) o2;
                ItemEntity itemEntity = new ItemEntity();
                MapUtil.mapKeySetLine2Upper(o21);
                Map2JavaBeanUtil.transMap2Bean(o21, itemEntity);
                itemEntity.setFbaProductId(fbaProductEntity.getFbaProductId());
                itemEntity.setSellerId(825);
                iItemService.save(itemEntity);
            }
            //同步预估到货时间表
            List<ArrivalEntity> list1 = arrivalService.list();
            for (ArrivalEntity arrivalEntity : list1) {
                String shipmentId = arrivalEntity.getShipmentId();
//                String asin = arrivalEntity.getAsin();
                FbaProductEntity byShipment = fbaProductService.getOne(new QueryWrapper<FbaProductEntity>().eq("shipment_id", shipmentId));
                if (byShipment.getShipmentStatus().equals("RECEIVING")) {
                    arrivalService.removeById(arrivalEntity.getArrivalId());
                }else {
                    if (arrivalEntity.getTimes().getTime()<= DateUtil.getNowDateDay().getTime()) {
                        arrivalService.update(new UpdateWrapper<ArrivalEntity>().eq("arrival_id",arrivalEntity.getArrivalId()).set("times",DateUtil.dayMath(DateUtil.getNowDateDay(),-1)));
                    }
                }
            }
            //同步实际到货状态
            saleService.flushSale();
//            }
        }



    }

    @Autowired
    ISellerService sellerService;

    @Override
    public void syncSeller() throws Exception {
        sellerService.remove(new QueryWrapper<SellerEntity>().ge("sel_id", 1));
        JSONObject js = OrderDemo.postForKing("/data/seller/lists", new HashMap<>());
        List<SellerEntity> saveList = new LinkedList();
        for (Object data : js.getJSONArray("data")) {
            Map<String, Object> o1 = (Map<String, Object>) data;
            SellerEntity fbaStoreEntity = new SellerEntity();
            MapUtil.mapKeySetLine2Upper(o1);
            Map2JavaBeanUtil.transMap2Bean(o1, fbaStoreEntity);
//            saveList.add(fbaStoreEntity);
//            if (saveList.size()==1000){
            sellerService.save(fbaStoreEntity);
//                saveList.clear();
//            }
        }
    }


    @Autowired
    IAccountService accountService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncAccount() throws Exception {
        accountService.remove(new QueryWrapper<AccountEntity>().ge("account_id", 1));
        JSONObject js = OrderDemo.postForKing("/data/account/lists", new HashMap<>());
        List<AccountEntity> saveList = new LinkedList();
        for (Object data : js.getJSONArray("data")) {
            Map<String, Object> o1 = (Map<String, Object>) data;
            AccountEntity fbaStoreEntity = new AccountEntity();
            MapUtil.mapKeySetLine2Upper(o1);
            Map2JavaBeanUtil.transMap2Bean(o1, fbaStoreEntity);
//            saveList.add(fbaStoreEntity);
//            if (saveList.size()==1000){
            accountService.save(fbaStoreEntity);
//                saveList.clear();
//            }
        }
//        if (saveList.size()>0){
//            accountService.saveBatch(saveList);
//        }
    }

    @Autowired
    IListingService listingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncListing() throws Exception {
        listingService.remove(new QueryWrapper<ListingEntity>().ge("list_id", 1));
        JSONObject js = OrderDemo.postForKing("/data/seller/lists", new HashMap<>());
        List<ListingEntity> saveList = new LinkedList();
        for (Object data : js.getJSONArray("data")) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            int sid = Integer.parseInt(String.valueOf(dataMap.get("sid")));
            Map<String, Object> body = new HashMap();
            body.put("offset", 0);
            body.put("length", Integer.MAX_VALUE);
            body.put("sid", sid);
            JSONObject jsonObject = OrderDemo.postForKing("/data/mws/listing", body);
            String code = jsonObject.getString("code");
            if (!code.equals("0")) {
                continue;
            }
            JSONArray data1 = jsonObject.getJSONArray("data");
//            JSONArray list = data1.getJSONArray("list");
            for (int i = 0; i < data1.size(); i++) {
                Object o = data1.get(i);
                Map<String, Object> o1 = (Map<String, Object>) o;
                ListingEntity fbaStoreEntity = new ListingEntity();
                MapUtil.mapKeySetLine2Upper(o1);
                Map2JavaBeanUtil.transMap2Bean(o1, fbaStoreEntity);
                fbaStoreEntity.setSellerId(sid);
                String principalInfo = fbaStoreEntity.getPrincipalInfo();
                JSONArray objects = JSON.parseArray(principalInfo);
                for (int i1 = 0; i1 < objects.size(); i1++) {
                    JSONObject jsonObject1 = objects.getJSONObject(i1);
                    fbaStoreEntity.setPrincipalUid(jsonObject1.getInteger("principal_uid"));
                    fbaStoreEntity.setPrincipalName(jsonObject1.getString("principal_name"));
                }
                String name = fbaStoreEntity.getLocalName();
                String type = getType(name);
                fbaStoreEntity.setType(type);
                saveList.add(fbaStoreEntity);
                if (saveList.size() == 1000) {
                    listingService.saveBatch(saveList);
                    saveList.clear();
                }
            }
        }
        if (saveList.size() > 0) {
            listingService.saveBatch(saveList);
        }
    }



    private  String getType(String name){
        String[]  strs = {"儿童平板","平板","相框"};
        String[]  strs1 = {"适配器","保护套"};

        for (String str : strs) {
            if (name.indexOf(str)!=-1) {
                return str;
            }
        }
        for (String s : strs1) {
            if (name.indexOf(s)!=-1) {
                return "配件";
            }
        }

        return "其他";
    }



}
