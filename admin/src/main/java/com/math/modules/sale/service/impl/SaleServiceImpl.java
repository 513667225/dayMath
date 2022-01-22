package com.math.modules.sale.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.math.modules.arrival.service.IArrivalService;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.in.entity.InEntity;
import com.math.modules.in.service.IInService;
import com.math.modules.item.entity.ItemEntity;
import com.math.modules.item.service.IItemService;
import com.math.modules.sync.entity.SyncEntity;
import com.math.modules.sync.service.ISyncService;
import com.math.modules.updateSale.entity.UpdateSaleEntity;
import com.math.modules.updateSale.service.IUpdateSaleService;
import com.yph.entity.AgingEntity;
import com.math.modules.aging.service.IAgingService;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.out.entity.OutEntity;
import com.math.modules.out.service.IOutService;
import com.math.modules.sale.entity.SaleEntity;
import com.math.modules.sale.entity.SaleModel;
import com.math.modules.sale.mapper.SaleMapper;
import com.math.modules.sale.service.ISaleService;
import com.yph.util.*;
import com.yph.util.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-11-12
 */
@Service
public class SaleServiceImpl extends ServiceImpl<SaleMapper, SaleEntity> implements ISaleService {

    @Autowired
    SaleMapper saleMapper;
    @Autowired
    IOutService outService;
    @Autowired
    INowService nowService;
    @Autowired
    IAgingService agingService;

    @Autowired
    IInService inService;


    @Override
    public R upload(MultipartFile file, P param) throws Exception {
        List<SaleModel> info = EasyExcelUtil.readExcelWithModel(file.getInputStream(), SaleModel.class, ExcelTypeEnum.valueOf(file.getInputStream()));
        Map<String, String> sends = new HashMap<>();
        for (SaleModel agingModel : info) {
            if (agingModel.getAsin() == null) {
                continue;
            }
            SaleEntity entity = new SaleEntity();
            entity.setAsin(agingModel.getAsin());
            entity.setProductName(agingModel.getName());
            entity.setSalePrice(agingModel.getSale());
            entity.setSite(agingModel.getSite());
            entity.setTimes(agingModel.getTimes());
            entity.setUser(agingModel.getUser());
            entity.setActualSale(agingModel.getAaSale());
            SaleEntity one = getOne(new QueryWrapper<SaleEntity>().eq("asin", entity.getAsin()).eq("site", entity.getSite()).eq("times", entity.getTimes()));
            if (one != null) {
                UpdateSaleEntity updateSaleEntity = new UpdateSaleEntity();
                updateSaleEntity.setAfterVal(agingModel.getSale());
                updateSaleEntity.setBeforeVal(one.getSalePrice());
                one.setSalePrice(agingModel.getSale());
                updateSaleEntity.setTimes(new Date());
                updateSaleEntity.setUser(param.getUserDo().getUsername());
                updateSaleEntity.setAsin(agingModel.getAsin());
                updateSaleEntity.setSite(agingModel.getSite());
                updateSaleEntity.setSaleId(one.getSaleId());
                updateSaleEntity.setTimes1(one.getTimes());
                updateSaleService.save(updateSaleEntity);

                BigDecimal divide = updateSaleEntity.getAfterVal().multiply(new BigDecimal("100")).divide(updateSaleEntity.getBeforeVal(), 2, BigDecimal.ROUND_HALF_UP);
                if (divide.intValue() < 80 || divide.intValue() > 120) {
                    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateSaleEntity.getTimes());
                    String value = "\nASIN:" + updateSaleEntity.getAsin() + "国家:" + updateSaleEntity.getSite() + "时间:" + format + "操作人:" + updateSaleEntity.getUser() + "修改前:" + updateSaleEntity.getBeforeVal() + "修改后:" + updateSaleEntity.getAfterVal();
                    String s = sends.get(updateSaleEntity.getUser());
                    if (s == null) {
                        s = value;
                    } else {
                        s += value;
                    }
                    sends.put(updateSaleEntity.getUser(), s);
                }
                one.setActualSale(null);
                updateById(one);
                continue;
            }

            P p = new P();
            p.put("asin", entity.getAsin());
            p.put("site", entity.getSite());
            p.put("times", entity.getTimes());
            Integer one1 = outService.sumSaleByOut(p);
            if (one1 != null) {
                entity.setActualSale(new BigDecimal(one1 + ""));
            }
            save(entity);
        }
        for (Map.Entry<String, String> stringStringEntry : sends.entrySet()) {
            String value = stringStringEntry.getValue();
            //TODO 修改收件人
            Mail.senMessage("系统警报", "预估销量修改超出阈值:" + value, "2631605104@qq.com");
        }

        return R.success();
    }

    @Override
    public R queryAbsSale(P p) {
        List<Map<String, Object>> mapList = saleMapper.queryAbsSale(p);
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Map<String, Object> maps : mapList) {
            String avgSale = String.valueOf(maps.get("salePrice"));
            String saleCount = String.valueOf(maps.get("saleCount"));
            String nowCount = String.valueOf(maps.get("nowCount"));
            //日均销量
            BigDecimal bigDecimal;
            if (avgSale.equals("null")) {
                bigDecimal = new BigDecimal("0");
            } else {
                bigDecimal = new BigDecimal(avgSale);
            }
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            //天数
            BigDecimal saleCountBig = saleCount.equals("null") ? new BigDecimal("0") : new BigDecimal(saleCount);
            //剩余个数
            BigDecimal nowCountBig = nowCount.equals("null") ? new BigDecimal("0") : new BigDecimal(nowCount);
            //可供天数
            BigDecimal okDay = nowCountBig.divide(bigDecimal, 2, BigDecimal.ROUND_HALF_UP);
            //海运需要个数
            BigDecimal hallCount = bigDecimal.multiply(new BigDecimal("35"));
            //空运需要个数
            BigDecimal flyCount = bigDecimal.multiply(new BigDecimal("15"));
            //快运需要个数
            BigDecimal sfCount = bigDecimal.multiply(new BigDecimal("10"));
            Map<String, Object> map = new HashMap<>();
            map.put("bigDecimal", bigDecimal);
            map.put("okDay", maps.get("okDay"));
            map.put("hallCount", hallCount);
            map.put("flyCount", flyCount);
            map.put("sfCount", sfCount);
            map.put("nowCountBig", nowCountBig);
            map.put("asin", maps.get("asin"));
            map.put("site", maps.get("site"));
            map.put("user", maps.get("user"));
            map.put("count", maps.get("count"));
            map.put("productName", maps.get("productName"));
            returnList.add(map);
        }

        return R.success().data(returnList).set("total", saleMapper.queryAbsSaleCount(p));
    }

    @Override
    public R querySumSale(P p) {
        List<Map<String, Object>> mapList = saleMapper.querySumSale(p);
        return R.success().data(mapList).set("total", saleMapper.querySumSaleCount(p));
    }

    @Override
    public R querySumSaleByAsin(P p) {
        List<Map<String, Object>> mapList = saleMapper.querySumSaleByAsin(p);
        return R.success().data(mapList).set("total", saleMapper.querySumSaleCountByAsin(p));
    }


    @Autowired
    IArrivalService arrivalService;

    @Override
    @Transactional
    public R mathInProduct(P p) {
        Page<Map<String, Object>> tPage = new Page<>(p.getInt("page"), p.getInt("limit"));
        QueryWrapper<NowEntity> objectQueryWrapper = new QueryWrapper<>();

        String asin = p.getString("asin");
        if (asin != null)
            objectQueryWrapper.eq("asin", asin);

        String site = p.getString("site");
        if (site != null)
            objectQueryWrapper.eq("site", site);

        String user = p.getString("user");
        if (user != null)
            objectQueryWrapper.eq("user", user);

        nowService.pageMaps(tPage, objectQueryWrapper);
        List<Map<String, Object>> records = tPage.getRecords();
        for (Map<String, Object> nowEntity : records) {
            Integer nowCount = Integer.parseInt(String.valueOf(nowEntity.get("now_count")));
            //todo 偷懒了
//            List<InEntity> one1 = inService.list(new QueryWrapper<InEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site")).eq("add_sign", 0));
//            for (InEntity inEntity : one1) {
//                nowCount+=inEntity.getCount();
//            }
            QueryWrapper<AgingEntity> agingEntityQueryWrapper = new QueryWrapper<AgingEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site"));
            AgingEntity agingEntity = agingService.getOne(agingEntityQueryWrapper);
            if (agingEntity == null) {
                agingEntity = new AgingEntity();
                agingEntity.setSecurityDay(14);
                agingEntity.setExpressDay(14);
                agingEntity.setAirDay(19);
                agingEntity.setSeaDay(39);
            }
            Date nowDateDay = DateUtil.dayMath(DateUtil.getNowDateDay(), -agingEntity.getExpressDay());
            //安全库存的日期

            Date securityTime = DateUtil.dayMath(nowDateDay, -agingEntity.getSecurityDay());
            P queryP1 = new P();
            queryP1.put("asin", nowEntity.get("asin"));
            queryP1.put("site", nowEntity.get("site"));
            if (new Date().getTime() > securityTime.getTime()) {
                queryP1.put("begin", securityTime);
                queryP1.put("end", nowDateDay);
            } else {
                queryP1.put("end", securityTime);
                queryP1.put("begin", nowDateDay);
            }
            //安全库存
            Integer integer1 = outService.sumSaleByDate(queryP1);
            //需要到货的时间
            Date needInProduct = nowDateDay;
            Integer addInProduct = 0;
//            Integer nowIn = outService.queryInByDate(DateUtil.getNowDateDay(), nowDateDay);
//            nowIn = nowIn == null ? 0 : nowIn;
            P getInByTime = new P();
            getInByTime.put("asin",nowEntity.get("asin"));
            getInByTime.put("site",nowEntity.get("site"));
            getInByTime.put("begin",DateUtil.getNowDateDay());
            getInByTime.put("end",securityTime);
            Integer inByTime = arrivalService.getInByTime(getInByTime);
            Integer nowOut = outService.queryOutByDate(DateUtil.getNowDateDay(), nowDateDay,nowEntity.get("asin"),nowEntity.get("site"));
            Date lastTime = null;
            Integer overCount = null;
            Integer tempNow = nowCount+inByTime + nowOut;
            QueryWrapper<SaleEntity> eq = new QueryWrapper<SaleEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site"));
            if (tempNow > integer1) {
                eq.ge("times", nowDateDay);
                eq.orderByAsc("times");
                List<SaleEntity> one = list(eq);
                Integer sum = 0;
                for (SaleEntity saleEntity : one) {
                    P qq1 = new P();
                    qq1.put("asin", nowEntity.get("asin"));
                    qq1.put("site", nowEntity.get("site"));
                    qq1.put("begin", saleEntity.getTimes());
                    qq1.put("end", DateUtil.dayMath(saleEntity.getTimes(), -agingEntity.getSecurityDay()));
                    //安全库存
                    Integer temp = outService.sumSaleByDate(qq1);
                    sum += saleEntity.getSalePrice().intValue();
                    if (saleEntity.getTimes().getTime()>securityTime.getTime()){
                        List<ArrivalEntity> list = arrivalService.list(new QueryWrapper<ArrivalEntity>().eq("asin", nowEntity.get("asin")).eq("times", saleEntity.getTimes()).eq("country", saleEntity.getSite()));
                        for (ArrivalEntity arrivalEntity : list) {
                            tempNow += arrivalEntity.getCount();
                        }
                    }
                    if (sum + temp > tempNow) {
                        break;
                    }
                    needInProduct = saleEntity.getTimes();
                }
//                needInProduct = DateUtil.dayMath(needInProduct,agingEntity.getSecurityDay());
            } else {
//                addInProduct = integer1 - nowCount;
                eq.ge("times", DateUtil.getNowDateDay());
                eq.orderByAsc("times");
                List<SaleEntity> one = list(eq);
                Integer sum = 0;
                for (SaleEntity saleEntity : one) {

                    BigDecimal actualSale = saleEntity.getActualSale();
                    sum += actualSale.intValue() == 0 ? saleEntity.getSalePrice().intValue() : actualSale.intValue();
                    if (sum > nowCount) {
//                        overCount = sum -nowCount;
                        lastTime = saleEntity.getTimes();
                        break;
                    }

                }
//                needInProduct = DateUtil.dayMath(needInProduct,agingEntity.getSecurityDay());
            }
            nowEntity.put("needInProduct", needInProduct);
            nowDateDay = DateUtil.getNowDateDay();
            Integer integer = DateUtil.daySubDay(needInProduct, nowDateDay);
            //推荐物流类型
            String type = AgingUtil.type(integer);
            nowEntity.put("type", type);
            //物流方式相对应的天数
            Integer entityDay = AgingUtil.getEntityDay(agingEntity, type);
            //应该发货的时间
            Date outProduct = DateUtil.dayMath(needInProduct, entityDay);
            nowEntity.put("outProduct", outProduct);
            //预计快递到达时间
            Date date = DateUtil.dayMath(outProduct, -entityDay);
            if (lastTime != null) {
                //会断货
//                UpdateWrapper<SaleEntity> eq = new UpdateWrapper<SaleEntity>().set("sale_price",overCount).eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site")).eq("times",lastTime);
//                update(eq);
                UpdateWrapper<SaleEntity> eq1 = new UpdateWrapper<SaleEntity>().set("sale_price", 0).eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site")).gt("times", lastTime).lt("times", date);
                update(eq1);
            }
            nowEntity.put("expTime", date);
            Integer integer2 = AgingUtil.needInDay(agingEntity, type);
            P queryP2 = new P();
            queryP2.put("asin", nowEntity.get("asin"));
            queryP2.put("site", nowEntity.get("site"));
            queryP2.put("begin", outProduct);
            queryP2.put("end", DateUtil.dayMath(outProduct, -integer2));
            //快递应该发货的数量

            P qq1 = new P();
            qq1.put("asin", nowEntity.get("asin"));
            qq1.put("site", nowEntity.get("site"));
            qq1.put("begin", DateUtil.dayMath(outProduct, -integer2));
            qq1.put("end", DateUtil.dayMath(DateUtil.dayMath(outProduct, -integer2), -agingEntity.getSecurityDay()));
            //安全库存
            Integer temp = outService.sumSaleByDate(qq1);
            P sumInByTime = new P();
            sumInByTime.put("asin",nowEntity.get("asin"));
            sumInByTime.put("site",nowEntity.get("site"));
            sumInByTime.put("begin",nowDateDay);
            sumInByTime.put("end",DateUtil.dayMath(outProduct, -integer2));
            Integer in = arrivalService.getInByTime(sumInByTime);
//            Integer in = outService.queryInByDate(nowDateDay, DateUtil.dayMath(outProduct, -integer2));
            in = in == null ? 0 : in;
            Integer out = outService.queryOutByDate(DateUtil.dayMath(nowDateDay, 1), DateUtil.dayMath(outProduct, -integer2),nowEntity.get("asin"),nowEntity.get("site"));
            Integer outCount = (temp - (nowCount - in + out));

            //outService.sumSaleByDate(queryP2);
            //x+(5517-3114)+未来五天销量
            nowEntity.put("outCount", outCount);
            if (outCount<0){
                nowEntity.put("outCount", 0);
            }
            nowEntity.put("lastTime", lastTime);
            threeMach(nowEntity, agingEntity, outProduct, nowDateDay, nowCount, outCount, type, DateUtil.dayMath(outProduct, -integer2));

        }

        return R.success().data(records).set("total", tPage.getTotal());
    }

    private void threeMach(Map<String, Object> nowEntity, AgingEntity agingEntity, Date outProduct, Date nowDateDay, Integer nowCount, Integer outCount, String type, Date lastIn) {
        //上一票的最后1次补货到货时间
        Date date = lastIn;
        if (!type.equals("海运")) {
            Integer seaOutCount = 0;
            P queryP3 = new P();
            queryP3.put("asin", nowEntity.get("asin"));
            queryP3.put("site", nowEntity.get("site"));
            queryP3.put("begin", outProduct);
            queryP3.put("end", DateUtil.dayMath(outProduct, -agingEntity.getSeaDay()));
            //海运期间应该发货的数量
            P sumInByTime = new P();
            sumInByTime.put("asin",nowEntity.get("asin"));
            sumInByTime.put("site",nowEntity.get("site"));
            sumInByTime.put("begin",nowDateDay);
            sumInByTime.put("end",DateUtil.dayMath(outProduct, -agingEntity.getSeaDay()));
            Integer ins = arrivalService.getInByTime(sumInByTime);
            ins = ins == null ? 0 : ins;
            Integer outs = outService.queryOutByDate(DateUtil.dayMath(nowDateDay, 1), DateUtil.dayMath(outProduct, -agingEntity.getSeaDay()),nowEntity.get("asin"),nowEntity.get("site"));
//            seaOutCount = outService.sumSaleByDate(queryP3);

            P qq2 = new P();
            qq2.put("asin", nowEntity.get("asin"));
            qq2.put("site", nowEntity.get("site"));
            qq2.put("begin", DateUtil.dayMath(outProduct, -agingEntity.getSeaDay()));
            qq2.put("end", DateUtil.dayMath(DateUtil.dayMath(outProduct, -agingEntity.getSeaDay()), -agingEntity.getSecurityDay()));
            //安全库存
            Integer temps = outService.sumSaleByDate(qq2);
            temps = (temps - (nowCount - ins + outs + outCount));
            seaOutCount = temps;
            nowEntity.put("seaOutCount", seaOutCount);
            nowEntity.put("nextType", AgingUtil.nextType(type));
            nowEntity.put("nextDate", DateUtil.dayMath(outProduct, -AgingUtil.needInDay(agingEntity, type)));
            date = DateUtil.dayMath(outProduct, -agingEntity.getSeaDay());
        }
        //next SeaOut
        P qq2 = new P();
        qq2.put("asin", nowEntity.get("asin"));
        qq2.put("site", nowEntity.get("site"));
        qq2.put("begin", date);
        qq2.put("end", DateUtil.dayMath(date, -agingEntity.getSeaDay()));
        //维持海运所需要发的数量
        Integer temps = outService.sumSaleByDate(qq2);
        nowEntity.put("lastSeaCount", temps);
        nowEntity.put("lastSeaDate", date);
    }


    @Override
    public R mathInProductByAsin(P p) {
        Map<String, Object> nowEntity = nowService.getMap(new QueryWrapper<NowEntity>().eq("asin", p.getString("asin")).eq("site", p.getString("site")));
        Date nowDateDay = DateUtil.getNowDateDay();
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(nowEntity);
        Integer nowCount = Integer.parseInt(String.valueOf(nowEntity.get("now_count")));
        QueryWrapper<AgingEntity> agingEntityQueryWrapper = new QueryWrapper<AgingEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site"));
        AgingEntity agingEntity = agingService.getOne(agingEntityQueryWrapper);
        //安全库存的日期
        Date securityTime = DateUtil.dayMath(nowDateDay, -agingEntity.getSecurityDay());
        P queryP1 = new P();
        queryP1.put("asin", nowEntity.get("asin"));
        queryP1.put("site", nowEntity.get("site"));
        if (new Date().getTime() > securityTime.getTime()) {
            queryP1.put("begin", securityTime);
            queryP1.put("end", nowDateDay);
        } else {
            queryP1.put("end", securityTime);
            queryP1.put("begin", nowDateDay);
        }
        //安全库存
        Integer integer1 = outService.sumSaleByDate(queryP1);
        //需要到货的时间
        Date needInProduct = nowDateDay;
        Integer addInProduct = 0;
        if (nowCount > integer1) {
            //如果当前剩余库存大于安全库存，则判断能卖到哪天则为到货日期
            Integer sub = nowCount - integer1;
            QueryWrapper<SaleEntity> eq = new QueryWrapper<SaleEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site"));
            eq.ge("times", securityTime);
            eq.orderByAsc("times");
            List<SaleEntity> one = list(eq);
            Integer sum = 0;
            for (SaleEntity saleEntity : one) {
                sum += saleEntity.getSalePrice().intValue();
                if (sum > sub) {
                    break;
                }
                needInProduct = saleEntity.getTimes();
            }
            needInProduct = DateUtil.dayMath(needInProduct, agingEntity.getSecurityDay());
        } else {
            addInProduct = integer1 - nowCount;

            QueryWrapper<SaleEntity> eq = new QueryWrapper<SaleEntity>().eq("asin", nowEntity.get("asin")).eq("site", nowEntity.get("site"));
            eq.le("times", nowDateDay);
            eq.orderByDesc("times");
            List<SaleEntity> one = list(eq);
            Integer sum = 0;
            for (SaleEntity saleEntity : one) {

                BigDecimal actualSale = saleEntity.getActualSale();
                sum += actualSale.intValue() == 0 ? saleEntity.getSalePrice().intValue() : actualSale.intValue();
                if (sum > addInProduct) {
                    break;
                }
                needInProduct = saleEntity.getTimes();
            }
//                needInProduct = DateUtil.dayMath(needInProduct,agingEntity.getSecurityDay());
        }
        nowEntity.put("needInProduct", needInProduct);
        Integer integer = DateUtil.daySubDay(needInProduct, nowDateDay);
        //推荐物流类型
        String type = AgingUtil.type(integer);
        nowEntity.put("type", type);
        //物流方式相对应的天数
        Integer entityDay = AgingUtil.getEntityDay(agingEntity, type);
        //应该发货的时间
        Date outProduct = DateUtil.dayMath(needInProduct, entityDay);
        nowEntity.put("outProduct", outProduct);
        //预计快递到达时间
        Date date = DateUtil.dayMath(outProduct, -entityDay);
        nowEntity.put("expTime", date);
        Integer integer2 = AgingUtil.needInDay(agingEntity, type);
        P queryP2 = new P();
        queryP2.put("asin", nowEntity.get("asin"));
        queryP2.put("site", nowEntity.get("site"));
        queryP2.put("begin", outProduct);
        queryP2.put("end", DateUtil.dayMath(outProduct, -integer2));
        //应该发货的数量
        Integer outCount = outService.sumSaleByDate(queryP2);
        outCount = outCount + addInProduct;
        nowEntity.put("outCount", outCount);

        return null;
    }

    @Autowired
    IUpdateSaleService updateSaleService;

    @Override
    @Transactional
    public R update(P p) throws Exception {
        Integer id = p.getInt("id");
        SaleEntity sizeEntity = p.thisToEntity(SaleEntity.class);
        SaleEntity byId = getById(id);
        UpdateSaleEntity updateSaleEntity = new UpdateSaleEntity();
        updateSaleEntity.setBeforeVal(byId.getSalePrice());
        updateSaleEntity.setAfterVal(sizeEntity.getSalePrice());
        updateSaleEntity.setTimes(new Date());
        updateSaleEntity.setAsin(byId.getAsin());
        updateSaleEntity.setSite(byId.getSite());
        updateSaleEntity.setUser(p.getUserDo().getUsername());
        updateSaleEntity.setTimes1(byId.getTimes());
        updateSaleEntity.setSaleId(byId.getSaleId());
        sizeEntity.setSaleId(id);
        sizeEntity.setActualSale(null);
        updateById(sizeEntity);
        updateSaleService.save(updateSaleEntity);
        BigDecimal divide = updateSaleEntity.getAfterVal().multiply(new BigDecimal("100")).divide(updateSaleEntity.getBeforeVal(), 2, BigDecimal.ROUND_HALF_UP);
        if (divide.intValue() < 80 || divide.intValue() > 120) {
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateSaleEntity.getTimes());
            String value = "\nASIN:" + updateSaleEntity.getAsin() + "国家:" + updateSaleEntity.getSite() + "时间:" + format + "操作人:" + updateSaleEntity.getUser() + "修改前:" + updateSaleEntity.getBeforeVal() + "修改后:" + updateSaleEntity.getAfterVal();
            Mail.senMessage("系统警报", "预估销量修改超出阈值:" + value, "2631605104@qq.com");
        }
        return R.success();
    }

    @Override
    public R flush() throws Exception {
        List<SaleEntity> times = list(new QueryWrapper<SaleEntity>().eq("times", DateUtil.dayMath(DateUtil.getNowDateDay(), 1)));
        Map<String, String> sendMap = new HashMap<>();
        for (SaleEntity time : times) {
            if (time.getSalePrice().doubleValue() != 0) {
                time.setRate(time.getActualSale().multiply(new BigDecimal("100")).divide(time.getSalePrice(), 2, BigDecimal.ROUND_HALF_UP));
            } else {
                time.setRate(new BigDecimal(0));
            }
            if (time.getRate().intValue() < 90 || time.getRate().intValue() > 110) {

                String str = "\nASIN:" + time.getAsin() + "国家:" + time.getSite() + "品名:" + time.getProductName() + "负责人:" + time.getUser() + "预估销量:" + time.getSalePrice() + "实际销量:" + time.getActualSale();
                String s = sendMap.get(time.getUser());
                if (s == null) {
                    s = str;
                } else {
                    s += str;
                }
                sendMap.put(time.getUser(), s);
            }
        }
        for (Map.Entry<String, String> stringStringEntry : sendMap.entrySet()) {
            //TODO 修改收件人
            String value = stringStringEntry.getValue();
            Mail.senMessage("系统警报", "以下业绩完成率浮动超过10%:" + value, "2631605104@qq.com");
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> querySalePage(P p) {
        return saleMapper.querySalePage(p);
    }

    @Override
    public Map<String, Object> querySalePageCount(P p) {
        return saleMapper.querySalePageCount(p);
    }


    @Autowired
    IFbaProductService fbaProductService;

    @Autowired
    IItemService iItemService;

    @Autowired
    ISyncService syncService;

    @Override
    @Transactional
    public void flushSale() {
        P p = new P();
        p.put("rowIndex", 0);
        p.put("limit", Integer.MAX_VALUE);
        p.put("begin", DateUtil.getNowDateDay());
        p.put("end", DateUtil.getNowDateDay());
        List<Map<String, Object>> mapList1 = querySalePage(p);
        for (Map<String, Object> record : mapList1) {
            String asin = (String) record.get("asin");
            String site = (String) record.get("site");
            P query = new P();
            query.put("asin", asin);
            query.put("site", site);
            query.put("gmtModified", record.get("times"));
            query.put("rowIndex", 0);
            query.put("limit", Integer.MAX_VALUE);
            R r = fbaProductService.queryProductPage(query);
            if (r.get("data") == null) {
                continue;
            }
            Object data = JSON.toJSON(r.get("data"));
            JSONArray jsonObject = JSON.parseArray(data.toString());
            List<Map> mapList = JSONUtils.toArray(String.valueOf(record.get("in_json")), Map.class);
            if (mapList == null) {
                mapList = new ArrayList<>();
            }
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject map = jsonObject.getJSONObject(i);
                JSONArray item_list = map.getJSONArray("item_list");
                for (int j = 0; j < item_list.size(); j++) {

                    JSONObject item = item_list.getJSONObject(j);
                    String asin1 = item.getString("asin");
                    if (!asin.equals(asin1)) {
                        continue;
                    }
//                    Integer inCount = item.getInteger("inCount");
                    String shipmentId = map.getString("shipment_id");
                    SyncEntity one = syncService.getOne(new QueryWrapper<SyncEntity>().eq("shipment_id", shipmentId).eq("asin", asin1));


                    String sname = item.getString("name");

                    Integer quantityReceived = item.getInteger("quantityReceived");
                    if (quantityReceived.intValue() == 0) {
                        continue;
                    }
                    Map<String, Object> listMap = new HashMap();
                    listMap.put("asin", asin1);
                    listMap.put("name", sname);
                    listMap.put("shipmentId", shipmentId);
                    Integer inCount = 0;
                    if (one != null) {
                        inCount = one.getInCount();
                        syncService.updateById(one.setInCount(quantityReceived));
                    } else {
                        one = new SyncEntity();
                        one.setAsin(asin1);
                        one.setShipmentId(shipmentId);
                        one.setInCount(quantityReceived);
                        syncService.save(one);
                        continue;
                    }
                    int value = quantityReceived - inCount;
                    if (value == 0) {
                        continue;
                    }
                    listMap.put("quantityReceived", value);
//                    ItemEntity  itemEntity = new ItemEntity();
//                    itemEntity.setItemId(item.getInteger("itemId"));
//                    itemEntity.setInCount(quantityReceived);
//                    iItemService.updateById(itemEntity);

                    mapList.add(listMap);
                }
            }
            if (mapList.size() > 0) {
                String s = JSON.toJSONString(mapList);

                update(new UpdateWrapper<SaleEntity>().eq("site", record.get("site")).eq("times", DateUtil.dayMath(((Date) record.get("times")),1)).eq("asin",record.get("asin")).set("in_json", s));
            }


        }
    }

    @Override
    public R censusByRate(P p) {
        List<Map<String, Object>> map = saleMapper.censusByRate(p);
        List<BigDecimal> priceList = new ArrayList();
        List<Object> userList = new ArrayList();
        for (Map<String, Object> stringObjectMap : map) {
            String sale_price = String.valueOf(stringObjectMap.get("sale_price"));
            BigDecimal price = new BigDecimal(sale_price.equals("null")?"0":sale_price);
            String actual_sale = String.valueOf(stringObjectMap.get("actual_sale"));
            BigDecimal actual = new BigDecimal(actual_sale.equals("null")?"0":actual_sale);
            BigDecimal rate ;
            if (price.intValue()==0){
                rate = new BigDecimal(0);
            }else {
                rate = actual.divide(price,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));

            }
            priceList.add(rate);
            userList.add(stringObjectMap.get("user"));
        }
        Map<String,Object> hash = new HashMap<>();
        hash.put("keys",userList);
        hash.put("values",priceList);
        return R.success().data(hash);
    }

    @Override
    public R censusByAmount(P p) {
        List<Map<String, Object>> map = saleMapper.censusByAmount(p);
        Map<String,Object> hash = new HashMap<>();
        List<Object> priceList = new ArrayList();
        List<Object> userList = new ArrayList();
        for (Map<String, Object> stringObjectMap : map) {
            priceList.add(stringObjectMap.get("amount")) ;
            userList.add(stringObjectMap.get("user")) ;
        }

        hash.put("keys",userList);
        hash.put("values",priceList);
        return R.success().data(hash);
    }

    @Override
    public R censusByProduct(P p) {
        List<Map<String, Object>> map = saleMapper.censusByProduct(p);
        Map<String,Object> hash = new HashMap<>();
        List<Object> priceList = new ArrayList();
        List<Object> userList = new ArrayList();
        for (Map<String, Object> stringObjectMap : map) {
            priceList.add(stringObjectMap.get("volume")) ;
            userList.add(stringObjectMap.get("local_name")) ;
        }

        hash.put("keys",userList);
        hash.put("values",priceList);
        return R.success().data(hash);
    }

    @Override
    public R censusByCount(P p) {
        List<Map<String, Object>> map = saleMapper.censusByCount(p);
        Map<String,Object> hash = new HashMap<>();
        List<Object> priceList = new ArrayList();
        List<Object> userList = new ArrayList();
        for (Map<String, Object> stringObjectMap : map) {
            priceList.add(stringObjectMap.get("actualSale")) ;
            userList.add(stringObjectMap.get("user")) ;
        }

        hash.put("keys",userList);
        hash.put("values",priceList);
        return R.success().data(hash);
    }

    @Override
    public R censusByType(P p) {
        List<Map<String, Object>> map = saleMapper.censusByType(p);
        Map<String, Object> sumMap = saleMapper.censusByTypeSum(p);
        Map<String,Object> hash = new HashMap<>();
        List<Map<String,Object>> priceList = new ArrayList();
        List<Map<String,Object>> priceList1 = new ArrayList();

        for (Map<String, Object> stringObjectMap : map) {
            String amount = String.valueOf(stringObjectMap.get("amount"));
            String sumAmount = String.valueOf(sumMap.get("amount"));
            String volume = String.valueOf(stringObjectMap.get("volume"));
            String sumVolume = String.valueOf(sumMap.get("volume"));
            HashMap<String, Object> map1 = new HashMap<>();
            HashMap<String, Object> map2 = new HashMap<>();
            BigDecimal divide = new BigDecimal(amount).divide(new BigDecimal(sumAmount), 2, BigDecimal.ROUND_HALF_UP);
            map1.put("value", divide.multiply(new BigDecimal("100")));
            map1.put("name",stringObjectMap.get("type"));
            priceList.add(map1);
            BigDecimal divide1 = new BigDecimal(volume).divide(new BigDecimal(sumVolume), 2, BigDecimal.ROUND_HALF_UP);
            map2.put("value", divide1.multiply(new BigDecimal("100")));
            map2.put("name",stringObjectMap.get("type"));
            priceList1.add(map2) ;
        }
        hash.put("values",priceList);
        hash.put("values1",priceList1);
        return R.success().data(hash);
    }


    public static void main(String[] args) {
        String z = "z1";

        System.out.println(z.matches("z[0-9]+"));
    }

}
