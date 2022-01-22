package com.math.modules.in.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.in.entity.InEntity;
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
public interface IInService extends IService<InEntity> {
    R upload(MultipartFile file) throws Exception;

    R syncSize(String site) throws Exception;
}
