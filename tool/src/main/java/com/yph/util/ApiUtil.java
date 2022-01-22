package com.yph.util;

import com.yph.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Agu
 */
@Component
public class ApiUtil {


    RestTemplate restTemplate;

    @Autowired
    RedisService redisService;



    public ApiUtil() {
        restTemplate = new RestTemplate();
    }

    public Object testRest() {
        String rmbToMyRate = redisService.get("RmbToMyRate");
        if (rmbToMyRate == null || rmbToMyRate.equals("") ||rmbToMyRate.equals("null")) {
            R r = get();
            Map<String, Object> map = (Map<String, Object>) r.get("data");
            Map<String, Object> map1 = (Map<String, Object>) map.get("result");
            rmbToMyRate =  String.valueOf(map1.get("rate"));
            redisService.getValueOperations().set("RmbToMyRate",rmbToMyRate,24, TimeUnit.HOURS);
        }
        return rmbToMyRate;
    }


    public BigDecimal ticker(){
        String ticker = redisService.get("ticker");
        if (ticker == null || ticker.equals("") ||ticker.equals("null")){
            Map<String,Object> map = restTemplate.getForObject("https://www.bcone.vip/api/market/tickers/ticker?symbol=usdt_bpc", Map.class);
            Map<String,String> data = map.get("data")==null ? null: ((Map<String, String>) map.get("data"));
            if (data==null){
                return null;
            }
            String last = data.get("Last");
            redisService.getValueOperations().set("ticker",last,1, TimeUnit.DAYS);
            ticker = last;
        }
        return new BigDecimal(ticker).multiply(new BigDecimal(testRest().toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
    }//2  1013/2


    public boolean is1013(Long l){
        return is1013(new BigDecimal(l.toString()));
    }

    public boolean is1013(BigDecimal bigDecimal){
        if(LifeUtil.calibration(bigDecimal,ticker()).doubleValue()>99500){
            return true;
        }
        return false;
    }




    public R get() {
        Map forObject = restTemplate.getForObject("http://api.k780.com/?app=finance.rate&scur=USD&tcur=CNY&appkey=55770&sign=a7416766da2868ad155f642dc6633f37", Map.class);
        return R.success().data(forObject);
    }

}
