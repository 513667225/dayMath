package com.math.modules.saleSum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.saleSum.entity.SaleSumEntity;
import com.yph.util.P;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
public interface SaleSumMapper extends BaseMapper<SaleSumEntity> {



    List<Map<String,Object>> querySalePage(P p);
    Integer querySalePageCount(P p);


}
