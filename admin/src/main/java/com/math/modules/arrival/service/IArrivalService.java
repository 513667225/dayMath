package com.math.modules.arrival.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.yph.util.P;
import com.yph.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-01-10
 */
public interface IArrivalService extends IService<ArrivalEntity> {


    R save(P p) throws Exception ;


    Integer getInByTime(P p);
}
