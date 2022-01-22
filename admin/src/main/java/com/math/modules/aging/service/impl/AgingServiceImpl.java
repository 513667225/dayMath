package com.math.modules.aging.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yph.entity.AgingEntity;
import com.math.modules.aging.entity.AgingModel;
import com.math.modules.aging.mapper.AgingMapper;
import com.math.modules.aging.service.IAgingService;
import com.yph.util.EasyExcelUtil;
import com.yph.util.R;
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
public class AgingServiceImpl extends ServiceImpl<AgingMapper, AgingEntity> implements IAgingService {

    @Override
    @Transactional
    public R upload(MultipartFile file) throws Exception {
        List<AgingModel> info = EasyExcelUtil.readExcelWithModel(file.getInputStream(), AgingModel.class, ExcelTypeEnum.valueOf(file.getInputStream()));
        for (AgingModel agingModel : info) {
            if (agingModel.getAsin()==null){
                continue;
            }
            AgingEntity entity = new AgingEntity();
            entity.setAsin(agingModel.getAsin());
            entity.setSecurityDay(agingModel.getSecurityDay());
            entity.setSite(agingModel.getSite());
            save(entity);
        }
        return R.success();
    }
}
