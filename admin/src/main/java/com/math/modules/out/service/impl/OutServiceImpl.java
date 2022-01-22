package com.math.modules.out.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.out.entity.OutEntity;
import com.math.modules.out.entity.OutModel;
import com.math.modules.out.mapper.OutMapper;
import com.math.modules.out.service.IOutService;
import com.math.modules.sale.entity.SaleEntity;
import com.math.modules.sale.service.ISaleService;
import com.math.modules.size.entity.SizeEntity;
import com.math.modules.size.service.ISizeService;
import com.yph.util.EasyExcelUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2021-11-10
 */
@Service
public class OutServiceImpl extends ServiceImpl<OutMapper, OutEntity> implements IOutService {


    @Autowired
    INowService nowService;

    @Autowired
    ISizeService sizeService;
    @Autowired
    ISaleService saleService;

    @Autowired
    OutMapper outMapper;

    @Override
    public Integer queryOutByDate(Date nowDate, Date toDate, Object asin, Object site) {
        P p = new P();
        p.put("asin", asin);
        p.put("site", site);
        if (nowDate.getTime() > toDate.getTime()) {
            p.put("begin", toDate);
            p.put("end", nowDate);
            Integer integer = outMapper.queryOutByDate(p);
            return integer == null ? 0 : integer;
        } else {
            p.put("end", toDate);
            p.put("begin", nowDate);
            Integer integer = outMapper.queryOutByDate(p);
            return integer == null ? 0 : -integer;
        }
    }

    @Override
    public Integer queryOutByDate2(Date nowDate, Date toDate, Object asin, Object site) {
        P p = new P();
        p.put("asin", asin);
        p.put("site", site);
        p.put("end", toDate);
        p.put("begin", nowDate);
        Integer integer = outMapper.queryOutByDate(p);
        return integer == null ? 0 : -integer;

    }

    @Override
    public Integer queryInByDate(Date nowDate, Date toDate) {
        P p = new P();
        if (nowDate.getTime() > toDate.getTime()) {
            p.put("begin", toDate);
            p.put("end", nowDate);
            Integer integer = outMapper.queryInByDate(p);
            return integer == null ? 0 : -integer;
        } else {
            p.put("end", toDate);
            p.put("begin", nowDate);
            return 0;
        }

    }

    @Override
    public Integer sumSaleByDate(P p) {
        Integer integer = outMapper.sumSaleByDate(p);
        return integer == null ? 0 : integer;
    }

    @Override
    public Integer sumSaleByOut(P p) {
        Integer integer = outMapper.sumSaleByOut(p);
        return integer == null ? 0 : integer;
    }

    @Override
    public Integer sumSale(Date nowDate, Date toDate) {
        P p = new P();
        if (nowDate.getTime() > toDate.getTime()) {
            p.put("begin", toDate);
            p.put("end", nowDate);
            Integer integer = outMapper.sumAllSale(p);
            return integer == null ? 0 : integer;
        } else {
            p.put("end", toDate);
            p.put("begin", nowDate);
            Integer integer = outMapper.sumAllSale(p);
            return integer == null ? 0 : -integer;
        }
    }

    @Override
    @Transactional
    public R uploadOut(MultipartFile file) throws Exception {
        List<OutModel> info = EasyExcelUtil.readExcelWithModel(file.getInputStream(), OutModel.class, ExcelTypeEnum.valueOf(file.getInputStream()));
        for (OutModel outModel : info) {
            if (outModel.getSku() == null && outModel.getAsin() == null) {
                continue;
            }
            OutEntity entity = new OutEntity();
            entity.setTimes(outModel.getDateTime());
            entity.setAsin(outModel.getAsin());
            entity.setCountry(outModel.getCountry());
            entity.setFba(outModel.getFba());
            entity.setLable(outModel.getLabel());
            entity.setMsku(outModel.getMsku());
            entity.setName(outModel.getName());
            entity.setOrderCount(outModel.getOrderCount());
            entity.setParentasin(outModel.getParentasin());
            entity.setSaleVal(outModel.getSaleVal());
            entity.setShop(outModel.getShop());
            entity.setSku(outModel.getSku());
            entity.setTitle(outModel.getTitle());
            entity.setType(outModel.getType());
            entity.setUser(outModel.getUser());
            entity.setCode(outModel.getCode());

            QueryWrapper<OutEntity> eq = new QueryWrapper<OutEntity>().eq("code", entity.getCode());
            OutEntity one2 = getOne(eq);
            Integer product = entity.getSaleVal();
            if (one2 != null) {
                OutEntity update = new OutEntity();
                update.setOutId(one2.getOutId());
                update.setSaleVal(entity.getSaleVal());
                updateById(update);
                product = product - one2.getSaleVal();
            } else {
                save(entity);
            }

            NowEntity one = nowService.getOne(new QueryWrapper<NowEntity>().eq("asin", entity.getAsin()).eq("site", entity.getCountry()));
            SizeEntity one1 = sizeService.getOne(new QueryWrapper<SizeEntity>().eq("asin", entity.getAsin()).eq("site", entity.getCountry()));

            if (one1 == null) {
                SizeEntity sizeEntity = new SizeEntity();
                sizeEntity.setAsin(entity.getAsin());
                sizeEntity.setSizeCount(0);
                sizeEntity.setSite(entity.getCountry());
                sizeService.save(sizeEntity);
                one1 = sizeEntity;
            }

            if (one == null) {
                NowEntity sizeEntity = new NowEntity();
                sizeEntity.setAsin(entity.getAsin());
                sizeEntity.setNowCount(0);
                sizeEntity.setSite(entity.getCountry());
                nowService.save(sizeEntity);
                one = sizeEntity;
            }
            SaleEntity sale = saleService.getOne(new QueryWrapper<SaleEntity>().eq("asin", entity.getAsin()).eq("site", entity.getCountry()).eq("times", entity.getTimes()));
            if (sale != null) {
                UpdateWrapper<SaleEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.setSql("actual_sale=actual_sale+" + product).eq("sale_id", sale.getSaleId());
                saleService.update(updateWrapper);
            }
            UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("now_count=now_count-" + product).eq("now_id", one.getNowId());
            nowService.update(updateWrapper);
        }
        return R.success();
    }


}
