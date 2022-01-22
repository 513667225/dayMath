package com.math.modules.arrival.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.math.modules.arrival.entity.ArrivalEntity;
import com.math.modules.arrival.mapper.ArrivalMapper;
import com.math.modules.arrival.service.IArrivalService;
import com.math.modules.fbaProduct.mapper.FbaProductMapper;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.yph.util.DateUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-01-10
 */
@Service
public class ArrivalServiceImpl extends ServiceImpl<ArrivalMapper, ArrivalEntity> implements IArrivalService {


    @Autowired
    IFbaProductService fbaProductService;

    @Autowired
    FbaProductMapper fbaProductMapper;

    @Autowired
    ArrivalMapper arrivalMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R save(P p) {
        p.put("rowIndex",0);
        p.put("limit",1);
        Map<String, Object> map = fbaProductService.queryById(p);
        String shipmentId = String.valueOf(map.get("shipment_id"));
        String country = String.valueOf(map.get("country"));
        Map<String,Object> query = new HashMap<>();
        query.put("fba_product_id",map.get("fba_product_id"));
        List<Map<String,Object>> fba_product_id1 = fbaProductMapper.queryProductItemInfo(query);
        for (Map<String, Object> stringObjectMap : fba_product_id1) {
            ArrivalEntity arrivalEntity = new ArrivalEntity();
            arrivalEntity.setTimes(DateUtil.stringToDate(p.getString("times")));
            String asin = String.valueOf(stringObjectMap.get("asin"));
            Integer count = stringObjectMap.get("init_quantity_shipped")==null? 0:(Integer)stringObjectMap.get("init_quantity_shipped");
            arrivalEntity.setAsin(asin);
            arrivalEntity.setCount(count);
            arrivalEntity.setShipmentId(shipmentId);
            arrivalEntity.setCountry(country);
            save(arrivalEntity);
        }
        return R.success();
    }

    @Override
    public Integer getInByTime(P p) {


        return arrivalMapper.getInByTimes(p);
    }
}
