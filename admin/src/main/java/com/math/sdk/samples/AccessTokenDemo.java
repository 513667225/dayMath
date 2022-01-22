package com.math.sdk.samples;

import com.alibaba.fastjson.JSON;
import com.math.sdk.entity.Result;
import com.math.sdk.okhttp.AKRestClientBuild;
import com.math.sdk.sign.ApiSign;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenDemo {

    /**
     * <p>
     *     这个demo之合适 参数是以query-param形式传参的，如果有body形式传参参考orderDemo
     *     示例中的 appId，appSecret需要替换成客户自己申请的appId，appSecret endpoint
     * </p>
     */
    public static void main(String[] args) throws Exception {
        String appId = "ak_SSqCZunXgBcSH";
        // 如果用postman等其他工具调试时，需要将appSecret用urlencode.encode()进行转义
        String appSecret = "J6g7uASBoe3+IzayLq5PqY1q4dbAzPJZRMloGxRurGiO0zIMcW0xaLjI90PFfxuaBT4TodwXUsPvxH9R/S6CEXkHTlC5i19g1/CE927r44l0qRVC8tnoWAl7r9kjj+zi0kpKCdN1QS649EB9s/VJhAml7utwakQ68t5SjpYuc1vSJqaQfsAjH1z9f+VbpbX0G2s+VRutk1hRfbNlYLRRNR4CbE7k7/50sVIXNWumoTgGohzj04XfwIERpIjMH6X7LRZIfNB1QiyYmTG6qiy9d3zabm18y9Q8QJkDtv90iGV7SswlSryYjl+s6TBqZnniIkds43L8YQsqcNA5/0OMiA==";
        Result result = AKRestClientBuild.builder().endpoint("https://openapi.lingxing.com").getAccessToken(appId, appSecret);


        Map<String, String> map = new HashMap<>();
        map.put("age", "18");
        map.put("sex", "nan");

        Map<String, Object> param = new HashMap<>();
        param.put("number", JSON.toJSONString(map));
        param.put("id", "1");
        long timestamp = System.currentTimeMillis() / 1000;
        Object data = result.getData();
        Map map1 = (Map) data;

        param.put("timestamp", String.valueOf(timestamp));
        param.put("access_token", map1.get("access_token"));
        param.put("app_key", appId);
        String sign = ApiSign.sign(param, appId);
        param.put("sign", sign);
        Object sign1 = AKRestClientBuild.builder().endpoint("https://openapi.lingxing.com").sign(param);
    }

}
