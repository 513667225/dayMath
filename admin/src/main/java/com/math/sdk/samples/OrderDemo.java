package com.math.sdk.samples;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.math.sdk.core.Config;
import com.math.sdk.core.HttpMethod;
import com.math.sdk.core.HttpRequest;
import com.math.sdk.core.HttpResponse;
import com.math.sdk.okhttp.AKRestClientBuild;
import com.math.sdk.okhttp.HttpExecutor;
import com.math.sdk.sign.ApiSign;
import com.yph.util.DateUtil;
import com.yph.util.R;
import org.springframework.beans.factory.BeanFactory;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class OrderDemo {

    public static void main(String[] args) throws Exception {

//        BeanFactory
//        String appId = "ak_tVrFm2icLt3o0";
//
//        Map<String, Object> queryParam = new HashMap<>();
//        queryParam.put("timestamp", System.currentTimeMillis() / 1000 + "");
//        queryParam.put("access_token", "9faf5449-603c-467f-82d0-49858e49e43c");
//        queryParam.put("app_key", appId);
//
//        Map<String, Object> bod   y = new HashMap<>();
//        body.put("sid", 1);
//        body.put("start_date", "2021-07-03");
//        body.put("end_date", "2021-08-04");
//
//        Map<String, Object> signMap = new HashMap<>();
//        signMap.putAll(queryParam);
//        signMap.putAll(body);
//
//        String sign = ApiSign.sign(signMap, appId);
//        queryParam.put("sign", sign);
//
//
//        HttpRequest<Object> build = HttpRequest.builder(Object.class)
//                .method(HttpMethod.POST)
//                .endpoint("https://openapi.lingxing.com")
//                .path("erp/sc/data/mws_report/allOrders")
//                .queryParams(queryParam)
//                .json(JSON.toJSONString(body))
//                .build();
//        HttpResponse execute = HttpExecutor.create().execute(build);


        Map<String,Object> body = new HashMap();
//        body.put("offset",0);
//        body.put("length",-1);
//        body.put("sid", 825);
////        body.put("type", 2);
////        body.put("asin", "B094N9DYPM");
//        body.put("start_date", "2021-10-01");
//        body.put("end_date", DateUtil.getNowDateString(1));
        JSONObject jsonObject = OrderDemo.postForKing("/data/account/lists", body);
//        String code = jsonObject.getString("code");
//        JSONObject data = jsonObject.getJSONObject("data");
//        System.out.println(data.toJSONString());
    }

    public static JSONObject postForKing(String url, Map<String,Object> body) throws Exception {
//        Map<String, Object> body = new HashMap<>();
//        body.put("sid", 825);
//        body.put("offset", 0);
//        body.put("length", 100);
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("timestamp", System.currentTimeMillis() / 1000 + "");
        String accessToken1 = AKRestClientBuild.builder().getAccessToken();
        System.out.println("token:"+accessToken1);
        String accessToken = accessToken1;
        queryParam.put("access_token", accessToken);
        queryParam.put("app_key", Config.APPID);
        Map<String, Object> signMap = new HashMap<>();
        signMap.putAll(queryParam);
        signMap.putAll(body);
        String sign = ApiSign.sign(signMap, Config.APPID);
        queryParam.put("sign", sign);
        HttpRequest<Object> build = HttpRequest.builder(Object.class)
                .method(HttpMethod.POST)
                .endpoint("https://openapi.lingxing.com")
                .path("erp/sc"+url)
                .queryParams(queryParam)
//                .header("Content-Type", "application/json")
//                .header("Authorization", "bearer {{" + accessToken1 + "}}")
                .json(JSON.toJSONString(body))
                .build();
        HttpResponse execute = HttpExecutor.create().execute(build);
        String responseString = execute.getResponseString();
        System.out.println(responseString);
        JSONObject jsonObject = JSON.parseObject(responseString);
//        System.out.println(execute.getResponseString());
//        JSONObject data = jsonObject.getJSONObject("data");
        return jsonObject;
    }

}
