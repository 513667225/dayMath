package com.math.modules.arrival.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.yph.util.P;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-01-10
 */
public interface ArrivalMapper extends BaseMapper<ArrivalEntity> {


    Integer getInByTimes(P p);
}
