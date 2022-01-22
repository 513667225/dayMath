package com.math.modules.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.sale.entity.SaleEntity;
import com.yph.util.P;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-11-12
 */
public interface SaleMapper extends BaseMapper<SaleEntity> {


   List<Map<String,Object>> queryAbsSale(P p);
   Integer queryAbsSaleCount(P p);
   List<Map<String,Object>> querySumSale(P p);
   Integer querySumSaleCount(P p);
   List<Map<String,Object>> querySumSaleByAsin(P p);
   List<Map<String,Object>> querySalePage(P p);
   Map<String,Object> querySalePageCount(P p);
   Integer querySumSaleCountByAsin(P p);
   List<Map<String,Object>> censusByRate(P p);
   List<Map<String,Object>> censusByAmount(P p);

   List<Map<String,Object>> censusByProduct(P p);
   List<Map<String,Object>> censusByCount(P p);
   List<Map<String,Object>> censusByType(P p);
   Map<String,Object> censusByTypeSum(P p);





}
