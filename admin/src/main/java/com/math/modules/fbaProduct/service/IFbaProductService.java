package com.math.modules.fbaProduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.fbaProduct.entity.FbaProductEntity;
import com.yph.util.P;
import com.yph.util.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
public interface IFbaProductService extends IService<FbaProductEntity> {


    R queryProductPage( P p);


    Map<String,Object> queryById(P p);



}
