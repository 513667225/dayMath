package com.math.modules.out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.out.entity.OutEntity;
import com.yph.util.P;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
public interface OutMapper extends BaseMapper<OutEntity> {


    Integer queryOutByDate(P p);

    Integer queryInByDate(P p);

    Integer sumSaleByDate(P p);

    Integer sumSaleByOut(P p);

    Integer sumAllSale(P p);


}
