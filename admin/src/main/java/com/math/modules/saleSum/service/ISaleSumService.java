package com.math.modules.saleSum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.saleSum.entity.SaleSumEntity;
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
public interface ISaleSumService extends IService<SaleSumEntity> {


    R querySalePage(P p);
}
