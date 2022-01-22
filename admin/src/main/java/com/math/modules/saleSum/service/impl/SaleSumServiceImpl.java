package com.math.modules.saleSum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.saleSum.entity.SaleSumEntity;
import com.math.modules.saleSum.mapper.SaleSumMapper;
import com.math.modules.saleSum.service.ISaleSumService;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
@Service
public class SaleSumServiceImpl extends ServiceImpl<SaleSumMapper, SaleSumEntity> implements ISaleSumService {


    @Autowired
    SaleSumMapper saleSumMapper;

    @Override
    public R querySalePage(P p) {
        return R.success().data(saleSumMapper.querySalePage(p)).set("total",saleSumMapper.querySalePageCount(p));
    }
}
