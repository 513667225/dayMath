package com.math.modules.aging.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yph.entity.AgingEntity;
import com.yph.util.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
public interface IAgingService extends IService<AgingEntity> {

    R upload(MultipartFile file) throws Exception;

}
