package com.math.modules.fbaStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.fbaStore.entity.FbaStoreEntity;
import com.yph.util.P;
import com.yph.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
public interface IFbaStoreService extends IService<FbaStoreEntity> {


    R getAllName();


    R queryStorePage(P p);
}
