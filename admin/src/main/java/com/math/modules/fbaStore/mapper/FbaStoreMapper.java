package com.math.modules.fbaStore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.math.modules.fbaStore.entity.FbaStoreEntity;
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
public interface FbaStoreMapper extends BaseMapper<FbaStoreEntity> {



    List<String > getAllName();


    List<Map<String,Object>> queryStorePage(P P);
    Integer queryStorePageCount(P P);



}
