package com.math.modules.out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.math.modules.out.entity.OutEntity;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
public interface IOutService extends IService<OutEntity> {


    Integer queryOutByDate(Date nowDate,Date toDate,Object asin,Object site);
    Integer queryOutByDate2(Date nowDate,Date toDate,Object asin,Object site);

    Integer queryInByDate(Date nowDate,Date toDate);

    Integer sumSaleByDate(P p);

    Integer sumSaleByOut(P p);

    Integer sumSale(Date nowDate, Date toDate);

    R uploadOut(MultipartFile file) throws Exception;
}
