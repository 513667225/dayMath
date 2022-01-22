package com.math.modules.in.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.in.entity.InEntity;
import com.math.modules.in.entity.InModel;
import com.math.modules.in.mapper.InMapper;
import com.math.modules.in.service.IInService;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.size.entity.SizeEntity;
import com.math.modules.size.service.ISizeService;
import com.yph.util.EasyExcelUtil;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
@Service
public class InServiceImpl extends ServiceImpl<InMapper, InEntity> implements IInService {


    @Autowired
    INowService nowService;

    @Autowired
    ISizeService sizeService;

    @Override
    @Transactional
    public R upload(MultipartFile file) throws Exception {
        List<InModel> info = EasyExcelUtil.readExcelWithModel(file.getInputStream(), InModel.class, ExcelTypeEnum.valueOf(file.getInputStream()));
        for (InModel agingModel : info) {
            if (agingModel.getAsin()==null||agingModel.getCode()==null){
                continue;
            }
            InEntity entity = new InEntity();
            entity.setAsin(agingModel.getAsin().trim());
            entity.setSite(agingModel.getSite().trim());
            entity.setCount(agingModel.getCount());
            entity.setShipmentId(agingModel.getShipmentId());
            entity.setTimes(agingModel.getTimes());
            entity.setCode(agingModel.getCode());

            if (getOne(new QueryWrapper<InEntity>().eq("code", entity.getCode())) != null) {
                continue;
            }

            save(entity);

            SizeEntity one1 = sizeService.getOne(new QueryWrapper<SizeEntity>().eq("asin", entity.getAsin()).eq("site", entity.getSite()));
            if (one1==null){
                SizeEntity sizeEntity = new SizeEntity();
                sizeEntity.setAsin(entity.getAsin());
                sizeEntity.setSizeCount(0);
                sizeEntity.setSite(entity.getSite());
                sizeService.save(sizeEntity);
                one1 = sizeEntity;
            }
            NowEntity one = nowService.getOne(new QueryWrapper<NowEntity>().eq("asin", entity.getAsin()).eq("site", entity.getSite()));
            if (one==null){
                NowEntity sizeEntity = new NowEntity();
                sizeEntity.setAsin(entity.getAsin());
                sizeEntity.setNowCount(0);
                sizeEntity.setSite(entity.getSite());
                nowService.save(sizeEntity);
                one  = sizeEntity;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String format = simpleDateFormat.format(date);
            Date now = simpleDateFormat.parse(format);
            if (entity.getTimes().getTime()<=now.getTime()) {
                UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.setSql("now_count=now_count+" + entity.getCount()).eq("now_id", one.getNowId());
                InEntity inEntity = new InEntity();
                inEntity.setInId(entity.getInId());
                inEntity.setAddSign(1);
                updateById(inEntity);
                nowService.update(updateWrapper);
            }
        }
        return R.success();
    }

    @Override
    @Transactional
    public R syncSize(String site) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        Date now = simpleDateFormat.parse(format);
        List<InEntity> list = list(new QueryWrapper<InEntity>().eq("times",now).eq("site",site));
        for (InEntity inEntity : list) {
            UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("add_sign",1).setSql("now_count=now_count+" + inEntity.getCount()).eq("asin", inEntity.getAsin()).eq("site", inEntity.getSite());
            nowService.update(updateWrapper);
        }
        return R.success();
    }


}
