package com.yph.util;

import com.yph.entity.ShopEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Agu
 */
public class ShopCommissionUtil {


    public static Map<String, BigDecimal> getByPrice(double price, ShopEntity shopEntity){
        Map<String,BigDecimal> map = new HashMap();
        BigDecimal word;
        BigDecimal img ;
        BigDecimal video;
        if (price>=100){
            word = shopEntity.getWordCommission1();
            img = shopEntity.getImgCommission1();
            video = shopEntity.getVideoCommission1();
        }else {
            word = shopEntity.getWordCommission();
            img = shopEntity.getImgCommission();
            video = shopEntity.getVideoCommission();
        }
        map.put("wordCommission",word);
        map.put("imgCommission",img);
        map.put("videoCommission",video);
        return map;
    }

}
