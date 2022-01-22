package com.math.modules.fbaProduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.arrival.entity.ArrivalEntity;
import com.math.modules.fbaProduct.entity.FbaProductEntity;
import com.math.modules.fbaProduct.mapper.FbaProductMapper;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.item.entity.ItemEntity;
import com.math.modules.item.service.IItemService;
import com.yph.util.DateUtil;
import com.yph.util.MapUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
@Service
public class FbaProductServiceImpl extends ServiceImpl<FbaProductMapper, FbaProductEntity> implements IFbaProductService {


    @Autowired
    FbaProductMapper fbaProductMapper;

    @Autowired
    IItemService iItemService;

    @Override
    public R queryProductPage(P p) {

        List<Integer> list = fbaProductMapper.queryProductItem(p);
        p.put("idList",list);
        if (list.size() > 0) {
            List<Map<String, Object>> mapList = fbaProductMapper.queryProductPage(p);
            for (Map<String, Object> map : mapList) {
                Object fba_product_id = map.get("fba_product_id");
                Map<String,Object> query = new HashMap<>();
                query.put("fba_product_id",fba_product_id);
                List<Map<String,Object>> fba_product_id1 = fbaProductMapper.queryProductItemInfo(query);
                for (Map<String, Object> stringObjectMap : fba_product_id1) {
                    try {
                        MapUtil.mapKeySetLine2Upper(stringObjectMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                map.put("item_list",fba_product_id1);
            }
            return R.success().data(mapList).set("total",fbaProductMapper.queryProductPageCount(p));
        }

        return R.success().data(new ArrayList<>()).set("total",0);
    }

    @Override
    public Map<String,Object> queryById(P p) {
        List<Map<String, Object>> mapList = fbaProductMapper.queryProductPage(p);

        return mapList.get(0);
    }


}
