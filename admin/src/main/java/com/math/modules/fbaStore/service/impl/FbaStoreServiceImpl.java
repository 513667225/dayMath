package com.math.modules.fbaStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.math.modules.fbaStore.entity.FbaStoreEntity;
import com.math.modules.fbaStore.mapper.FbaStoreMapper;
import com.math.modules.fbaStore.service.IFbaStoreService;
import com.yph.util.MapUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FbaStoreServiceImpl extends ServiceImpl<FbaStoreMapper, FbaStoreEntity> implements IFbaStoreService {


    @Autowired
    FbaStoreMapper fbaStoreMapper;

    @Override
    public R getAllName() {
        return R.success().data(fbaStoreMapper.getAllName());
    }

    @Override
    public R queryStorePage(P p) {
        List<Map<String, Object>> mapList = fbaStoreMapper.queryStorePage(p);
        for (Map<String, Object> map : mapList) {
            try {
                MapUtil.mapKeySetLine2Upper(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.success().data(mapList).set("total",fbaStoreMapper.queryStorePageCount(p));
    }
}
