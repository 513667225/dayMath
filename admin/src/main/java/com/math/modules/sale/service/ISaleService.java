package com.math.modules.sale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.sale.entity.SaleEntity;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-11-12
 */
public interface ISaleService extends IService<SaleEntity> {


    R upload(MultipartFile file,P param) throws Exception;

    R queryAbsSale(P p);


    R querySumSale(P p);
    R querySumSaleByAsin(P p);

    R mathInProduct(P p);

    R mathInProductByAsin(P p);

    R update(P p) throws Exception;


    R flush() throws Exception;

    List<Map<String,Object>> querySalePage(P p);
    Map<String,Object> querySalePageCount(P p);


    void flushSale();



    R censusByRate(P p);

    R censusByAmount(P p);

    R censusByProduct(P p);
    R censusByCount(P p);
    R censusByType(P p);


}
