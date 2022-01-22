package com.math.modules.fbaProduct.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.fbaProduct.entity.FbaProductEntity;
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
public interface FbaProductMapper extends BaseMapper<FbaProductEntity> {


    List<Map<String,Object>> queryProductPage (P p);


    Integer queryProductPageCount(P p);

    List<Integer> queryProductItem(P p);

    List<Map<String,Object>> queryProductItemInfo(Map<String,Object> p);
}