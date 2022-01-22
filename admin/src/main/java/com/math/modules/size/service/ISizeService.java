package com.math.modules.size.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.size.entity.SizeEntity;
import com.yph.util.P;
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
public interface ISizeService extends IService<SizeEntity> {
    public R upload(MultipartFile file) throws Exception;

    R update(P p) throws Exception;
}
