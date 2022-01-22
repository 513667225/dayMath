package com.yph.util;

import com.yph.entity.RateEntity;
import com.yph.param.RedisParamenter;
import com.yph.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Agu
 */
@Component
public class ShopRateUtil {


    @Autowired
    RedisService redisService;


    //新增商户时初始化默认汇率
    public void initShopRate(Integer shopId) {
        redisService.getHashOperations().putAll(RedisParamenter.SHOP_RATE_KEY + shopId.toString(), redisService.getAll(RedisParamenter.INIT_SHOP_RATE_KEY));
    }


    //push商家汇率
    public void pushRateByShopId(Integer shopId, RateEntity rateEntity) {
        redisService.getHashOperations().put(RedisParamenter.SHOP_RATE_KEY + shopId, rateEntity.getId(),JSONUtils.toString(rateEntity));
    }
    //修改商家汇率
    public void updateRateByShopId(Integer shopId, Map<String, Object> map) {
        Map<String, String> allRateByShop = getAllRateByShop(shopId);
        for (Map.Entry<String, String> stringStringEntry : allRateByShop.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            Object o = map.get(key);
            if (o==null){
                continue;
            }
            String strObj = String.valueOf(o);
            RateEntity rateEntity = JSONUtils.toObject(value, RateEntity.class);
            rateEntity.setRate(new BigDecimal(strObj));
            redisService.getHashOperations().put(RedisParamenter.SHOP_RATE_KEY + shopId, key,JSONUtils.toString(rateEntity));
        }
    }

    //获取商家所有汇率      11
    public Map<String, String> getAllRateByShop(Integer shopId) {
        return redisService.getAll(RedisParamenter.SHOP_RATE_KEY + shopId);
    }

    //获取商家某个汇率
    public RateEntity getRateByShop(Integer shopId, String rateName) {
        return JSONUtils.toObject(redisService.getHashOperations().get(RedisParamenter.SHOP_RATE_KEY + shopId, rateName), RateEntity.class);
    }


}
