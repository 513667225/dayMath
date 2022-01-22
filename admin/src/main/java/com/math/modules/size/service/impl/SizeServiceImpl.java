package com.math.modules.size.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.in.entity.InEntity;
import com.math.modules.in.entity.InModel;
import com.math.modules.now.entity.NowEntity;
import com.math.modules.now.service.INowService;
import com.math.modules.size.entity.SizeEntity;
import com.math.modules.size.entity.SizeModel;
import com.math.modules.size.mapper.SizeMapper;
import com.math.modules.size.service.ISizeService;
import com.yph.util.EasyExcelUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class SizeServiceImpl extends ServiceImpl<SizeMapper, SizeEntity> implements ISizeService {


    @Autowired
    INowService nowService;

    @Override
    @Transactional
    public R upload(MultipartFile file) throws Exception {
        List<SizeModel> info = EasyExcelUtil.readExcelWithModel(file.getInputStream(), SizeModel.class, ExcelTypeEnum.valueOf(file.getInputStream()));
        for (SizeModel agingModel : info) {
            if (agingModel.getAsin()==null){
                continue;
            }
            SizeEntity entity = new SizeEntity();
            entity.setAsin(agingModel.getAsin());
            entity.setSite(agingModel.getSite());
            entity.setSizeCount(agingModel.getCount());
            SizeEntity one = getOne(new QueryWrapper<SizeEntity>().eq("asin", entity.getAsin()).eq("site", entity.getSite()));
            int size = entity.getSizeCount();
            boolean updates = false;
            if (one != null) {
                size = size-one.getSizeCount();
                one.setSizeCount(entity.getSizeCount());
                one.setShipmentId(entity.getShipmentId());
                updateById(one);
              updates = true;
            }

            NowEntity one1 = nowService.getOne(new QueryWrapper<NowEntity>().eq("asin", entity.getAsin()).eq("site", entity.getSite()));
            if (one1==null){
                NowEntity sizeEntity = new NowEntity();
                sizeEntity.setAsin(entity.getAsin());
                sizeEntity.setNowCount(0);
                sizeEntity.setSite(entity.getSite());
                nowService.save(sizeEntity);
                one1  = sizeEntity;
            }
            UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("now_count=now_count+"+size).eq("now_id",one1.getNowId());
            nowService.update(updateWrapper);

            if (updates){
                continue;
            }
            save(entity);
        }
        return R.success();
    }

    @Override
    @Transactional
    public R update(P p) throws Exception {
        Integer id = p.getInt("id");
        SizeEntity sizeEntity = p.thisToEntity(SizeEntity.class);
        sizeEntity.setSizeId(id);
        SizeEntity byId = getById(id);
        NowEntity one1 = nowService.getOne(new QueryWrapper<NowEntity>().eq("asin", byId.getAsin()).eq("site", byId.getSite()));
        UpdateWrapper<NowEntity> updateWrapper = new UpdateWrapper<>();
        int size =sizeEntity.getSizeCount() -byId.getSizeCount();
        updateWrapper.setSql("now_count=now_count+"+size).eq("now_id",one1.getNowId());
        nowService.update(updateWrapper);
        updateById(sizeEntity);

        return R.success();
    }


}
