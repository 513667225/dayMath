package com.yph.enun;

import com.yph.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Agu
 */
@Component
public class SystemParameter {


    @Autowired
    RedisService redisService;

    //生命源转换能量源的比率
    public  BigDecimal LifeSourceToEEnergyRate;

    //直推
    public  BigDecimal directPush;
    //  间推
    public  BigDecimal indirectPush;

    /**
     * 得到所有的值
     * @return
     */
    public Map getSystemParameterAll(){
        return redisService.get("SystemParameter", HashMap.class);
    }

    public String getKeyValue(String key){
        Map<String,Object> systemParameterAll=getSystemParameterAll();
        if (systemParameterAll.get(key)!=null){
            return systemParameterAll.get(key).toString();
        }
        return "";

    }

    /**
     *
     * @param key
     * @param value
     */
    public void AddOrUpdateSystemParameter(String key,Object value){
        Map<String,Object> systemParameterAll=getSystemParameterAll();
        Map<String,Object> map=new HashMap<>();
        if (systemParameterAll==null){
            redisService.set("SystemParameter",map);
        }else {
            systemParameterAll.put(key,value);
            redisService.set("SystemParameter",systemParameterAll);
        }

    }



    public void delete(String key){
        Map<String,Object> systemParameterAll=getSystemParameterAll();
        if (systemParameterAll==null){
            systemParameterAll=new HashMap<>();
        }
        if (systemParameterAll.get(key)!=null){
            systemParameterAll.remove(key);
        }
        redisService.set("SystemParameter",systemParameterAll);
    }



    public BigDecimal getLifeSourceToEEnergyRate() {
        Map systemParameterAll = getSystemParameterAll();
        return new BigDecimal(systemParameterAll.get("LifeSourceToEEnergyRate").toString());
    }
    public BigDecimal getSpecialLifeSourceToEEnergyRate() {
        Map systemParameterAll = getSystemParameterAll();
        return new BigDecimal(systemParameterAll.get("specialLifeSourceToEEnergyRate").toString());
    }



    public void setLifeSourceToEEnergyRate(BigDecimal lifeSourceToEEnergyRate) {
        AddOrUpdateSystemParameter("LifeSourceToEEnergyRate",lifeSourceToEEnergyRate);
    }

    public BigDecimal getDirectPush() {
        Map systemParameterAll = getSystemParameterAll();
        return new BigDecimal(systemParameterAll.get("directPush").toString());

    }

    public void setDirectPush(BigDecimal directPush) {
        AddOrUpdateSystemParameter("directPush",directPush);
    }

    public BigDecimal getIndirectPush() {
        Map systemParameterAll = getSystemParameterAll();
        return new BigDecimal(systemParameterAll.get("indirectPush").toString());

    }

    public void setIndirectPush(BigDecimal indirectPush) {
        AddOrUpdateSystemParameter("indirectPush",indirectPush);
    }
}
