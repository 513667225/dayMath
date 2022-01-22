package com.math.modules.sale.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.math.modules.arrival.service.IArrivalService;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.in.entity.InEntity;
import com.math.modules.in.service.IInService;
import com.math.modules.monday.entity.MondayEntity;
import com.math.modules.monday.service.IMondayService;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.out.service.IOutService;
import com.math.modules.sale.entity.SaleEntity;
import com.math.modules.sale.mapper.SaleMapper;
import com.math.modules.sale.service.ISaleService;
import com.math.modules.size.entity.SizeEntity;
import com.math.modules.size.service.ISizeService;
import com.math.modules.updateSale.entity.UpdateSaleEntity;
import com.math.modules.updateSale.service.IUpdateSaleService;
import com.yph.annotation.Pmap;
import com.yph.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-11-12
 */
@RestController
@RequestMapping("/sale")
public class SaleController {


    @Autowired
    ISaleService service;


    @Autowired
    IOutService outService;

    @Autowired
    ISizeService sizeService;

    @Autowired
    IInService inService;

    @Autowired
    SaleMapper saleMapper;

    @Autowired
    IUpdateSaleService updateSaleService;

    @Autowired
    IFbaProductService fbaProductService;

    @Autowired
    IArrivalService arrivalService;

    @Autowired
    INowService nowService;


    @Autowired
    IMondayService mondayService;

    @RequestMapping("/list")
    public R page(@Pmap P p) throws Exception{
//        Page<SaleEntity> tPage = new Page(p.getInt("page"), p.getInt("limit"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String begin = p.getString("begin");
        if (begin!=null)
        p.put("begin",simpleDateFormat.parse(begin));

        String end = p.getString("end");
        if(end!=null)
        p.put("end",simpleDateFormat.parse(end));
        List<Map<String, Object>> mapList1 = service.querySalePage(p);

        for (Map<String,Object> record : mapList1) {
            MapUtil.mapKeySetLine2Upper(record);
            Object saleId = record.get("saleId");
            List<UpdateSaleEntity> list = updateSaleService.list(new QueryWrapper<UpdateSaleEntity>().eq("sale_id", saleId).orderByDesc("times"));
            if (list.size()>0){
                UpdateSaleEntity updateSaleEntity = list.get(0);
                record.put("updateTime",updateSaleEntity.getTimes());
                record.put("beforeVal",updateSaleEntity.getBeforeVal());
            }
            BigDecimal salePrice = record.get("salePrice") == null ? new BigDecimal(0) : new BigDecimal(String.valueOf(record.get("salePrice")));
            BigDecimal actualSale = record.get("actualSale") == null ? new BigDecimal(0) : new BigDecimal(String.valueOf(record.get("actualSale")));

            if (salePrice.doubleValue()!=0){
                record.put("rate",actualSale.multiply(new BigDecimal("100")).divide(salePrice,2, BigDecimal.ROUND_HALF_UP));
            }else {
                record.put("rate",new BigDecimal(0));
            }

            Date times = (Date) record.get("times");
            if (times.getTime()==DateUtil.dayMath(DateUtil.getNowDateDay(),1).getTime()){
              P param = new P();
                param.put("asin",record.get("asin"));
                param.put("site",record.get("site"));
                param.put("rowIndex",0);
                param.put("limit",1);
                List<Map<String, Object>> mapList = saleMapper.queryAbsSale(param);
                for (Map<String, Object> map : mapList) {
                    record.put("ks",new BigDecimal(String.valueOf(map.get("okDay"))).intValue());
                }
            }
            //预估时间的周一
                Date thisWeekMonday = DateUtil.getThisWeekMonday(times);
            //这周一
                Date weekMonday = DateUtil.getThisWeekMonday();

//            SizeEntity one = sizeService.getOne(new QueryWrapper<SizeEntity>().eq("asin", record.get("asin")).eq("site", record.get("site")));
            MondayEntity one = mondayService.getOne(new QueryWrapper<MondayEntity>().eq("asin", record.get("asin")).eq("site", record.get("site")).eq("times", thisWeekMonday));

            if (times.getTime()>=weekMonday.getTime()){
                one = mondayService.getOne(new QueryWrapper<MondayEntity>().eq("asin", record.get("asin")).eq("site", record.get("site")).eq("times", weekMonday));
                thisWeekMonday = weekMonday;
            }
//                Date parse =DateUtil.getNowDateDay();
            if (one != null) {
                Integer size = one.getCount();
                Integer nowOut =0;
                if (times.getTime()>=weekMonday.getTime()){
                     nowOut = outService.queryOutByDate2(thisWeekMonday, DateUtil.dayMath((Date) record.get("times"),1), record.get("asin"), record.get("site"));

                }else {
                    nowOut = outService.queryOutByDate(thisWeekMonday, DateUtil.dayMath((Date) record.get("times"),1), record.get("asin"), record.get("site"));
                }

                //                P getInByTime = new P();
//                getInByTime.put("asin",record.get("asin"));
//                getInByTime.put("site",record.get("site"));
//                getInByTime.put("rowIndex",0);
//                getInByTime.put("limit",Integer.MAX_VALUE);
//                Date nowDateDay = DateUtil.getNowDateDay();


//                boolean f = false;
//                if (nowDateDay.getTime()>times.getTime()){
//                    getInByTime.put("begin",times);
//                    getInByTime.put("end",DateUtil.dayMath(nowDateDay,1));
//                    f  =true;
//                }else {
//                    getInByTime.put("begin",nowDateDay);
//                    getInByTime.put("end",times);
//                }

//                R r = fbaProductService.queryProductPage(getInByTime);
//                Integer inByTime = 0;
//                if (r.get("data")!=null) {
//                    Object data = JSON.toJSON(r.get("data"));
//                    JSONArray jsonObject = JSON.parseArray(data.toString());
//                    for (int i = 0; i < jsonObject.size(); i++) {
//                        JSONObject shipment = jsonObject.getJSONObject(i);
//                        String shipment_status = shipment.getString("shipment_status");
//
//                        JSONArray itemList = shipment.getJSONArray("item_list");
//                        for (int i1 = 0; i1 < itemList.size(); i1++) {
//                            JSONObject item = itemList.getJSONObject(i1);
//                            Integer count = item.getInteger("quantityShippedLocal");
//                            String asin = item.getString("asin");
//                            if (shipment_status.equals("RECEIVING")&&asin.equals(record.get("asin"))){
//                                inByTime+=count==null?0:count;
//                            }
//                        }
//                    }
//                }
//                inByTime = f? -inByTime :inByTime;
                //            List<InEntity> one1 = inService.list(new QueryWrapper<InEntity>().eq("asin", record.get("asin")).eq("site", record.get("site")).eq("times", record.get("times")));
//            List<InEntity> one2 = inService.list(new QueryWrapper<InEntity>().eq("asin", record.get("asin")).eq("site", record.get("site")).lt("times",record.get("times")));

//                Integer needAddSize =0;
//            if (one1!=null){
//                for (InEntity inEntity : one1) {
//                    needAddSize += inEntity.getCount();
////                    size +=inEntity.getCount();
//                }
//            }
//            if (one2!=null){
//                for (InEntity inEntity : one2) {
////                    needAddSize += inEntity.getCount();
//                    size +=inEntity.getCount();
//                }
//            }
                nowOut= nowOut == null?0 :nowOut;



                int fbaFirstSize = size + nowOut;
//                if (((Date) record.get("times")).getTime()==DateUtil.getThisWeekMonday().getTime()){
//                    fbaFirstSize = size;
//                }
//            record.setEstimateSize(nowOut-);
//            Integer integer = outService.sumSale(parse, record.getTimes());
//            int i = size + integer;
                record.put("fbaFirstSize",fbaFirstSize);
                record.put("estimateSize",fbaFirstSize-salePrice.intValue());
                record.put("actualSize",fbaFirstSize- (actualSale==null?0:actualSale.intValue()));
                record.put("weekMonday",size);
            }
//            record.put("needAddSize",needAddSize);
            List<ArrivalEntity> arrivalList = arrivalService.list(new QueryWrapper<ArrivalEntity>().eq("asin", record.get("asin")).eq("times", record.get("times")).eq("country", record.get("site")));
            String retSumStr = "";
            Integer retSum = 0;
            for (ArrivalEntity arrivalEntity : arrivalList) {
                String shipmentId = arrivalEntity.getShipmentId();
                Integer count = arrivalEntity.getCount();
                retSumStr+=shipmentId+"/"+count+",";
                retSum+=count;
            }
            record.put("retSumStr",retSumStr);
            record.put("retSum",retSum);
        }
        Map<String, Object> map = service.querySalePageCount(p);
        if (mapList1.size()>0) {
            HashMap<String, Object> e = new HashMap<>();
            e.put("salePrice",map.get("sale_price"));
            e.put("asin","汇总");
            e.put("actualSale",map.get("actual_sale"));
            BigDecimal salePrice = map.get("sale_price") == null ? new BigDecimal(0) : new BigDecimal(String.valueOf(map.get("sale_price")));
            BigDecimal actualSale = map.get("actual_sale") == null ? new BigDecimal(0) : new BigDecimal(String.valueOf(map.get("actual_sale")));
            if (salePrice.doubleValue()!=0){
                e.put("rate",actualSale.multiply(new BigDecimal("100")).divide(salePrice,2, BigDecimal.ROUND_HALF_UP));
            }else {
                e.put("rate",new BigDecimal(0));
            }
            mapList1.add(e);
        }

        return R.success().data(mapList1).set("total",map.get("count"));
    }



    @RequestMapping("flushSale")
    public  R flushSale(){

        service.flushSale();
        return  R.success();
    }

    @RequestMapping("/del")
    public  R del(@Pmap P p){
        Integer id = p.getInt("id");
        service.removeById(id);
        return R.success();
    }

    @RequestMapping("/queryAbsSale")
    public  R queryAbsSale(@Pmap P p){

        return service.queryAbsSale(p);
    }

    @RequestMapping("/querySumSale")
    public  R querySumSale(@Pmap P p){

        p.put("begin",p.getSqlDateByString("begin"));
        p.put("end",p.getSqlDateByString("end"));
        return service.querySumSale(p);
    }

    @RequestMapping("/querySumSaleByAsin")
    public  R querySumSaleByAsin(@Pmap P p){

        p.put("begin",p.getSqlDateByString("begin"));
        p.put("end",p.getSqlDateByString("end"));
        return service.querySumSaleByAsin(p);
    }

    @RequestMapping("/update")
    public  R update(@Pmap P p) throws Exception{
        Integer id = p.getInt("id");
        SaleEntity sizeEntity = p.thisToEntity(SaleEntity.class);
        sizeEntity.setSaleId(id);
        return service.update(p);
    }

    @RequestMapping("/uploadInfo")
    public  R uploadInfo(@Pmap P p, MultipartFile file) throws Exception{

        return service.upload(file,p);
    }

    @RequestMapping("/mathInProduct")
    public  R mathInProduct(@Pmap P p){

        return service.mathInProduct(p);
    }



    @RequestMapping("/mathInProductByAsin")
    public  R mathInProductByAsin(@Pmap P p){

        return service.mathInProductByAsin(p);
    }


    @RequestMapping("/flush")
    public  R flush() throws Exception{

        return service.flush();
    }



    @RequestMapping("/censusRate")
    public R censusRate(@Pmap P p){

        p.put("begin",DateUtil.stringToDate(p.getString("begin")));
        p.put("end",DateUtil.stringToDate(p.getString("end")));
        return service.censusByRate(p);
    }


    @RequestMapping("/censusAmount")
    public R censusAmount(@Pmap P p){
        p.put("begin",DateUtil.stringToDate(p.getString("begin")));
        p.put("end",DateUtil.stringToDate(p.getString("end")));
        return service.censusByAmount(p);
    }

    @RequestMapping("/censusByProduct")
    public R censusByProduct(@Pmap P p){
        p.put("begin",DateUtil.stringToDate(p.getString("begin")));
        p.put("end",DateUtil.stringToDate(p.getString("end")));
        return service.censusByProduct(p);
    }

    @RequestMapping("/censusByCount")
    public  R censusByCount(@Pmap P p){
        p.put("begin",DateUtil.stringToDate(p.getString("begin")));
        p.put("end",DateUtil.stringToDate(p.getString("end")));
        return  service.censusByCount( p);
    }

    @RequestMapping("/censusByType")
    public  R censusByType(@Pmap P p){
        p.put("begin",DateUtil.stringToDate(p.getString("begin")));
        p.put("end",DateUtil.stringToDate(p.getString("end")));
        return  service.censusByType( p);
    }

}
