package com.math.sdk.okhttp;


import com.math.sdk.core.Config;
import com.math.sdk.entity.Result;
import com.yph.param.RedisParamenter;

import java.util.HashMap;
import java.util.Map;

public class AKRestClientBuild {

    public static AKRestClientBuilder builder() {
        return new AKRestClientBuilder().endpoint("https://openapi.lingxing.com");
    }

    public static class AKRestClientBuilder {

        private Config config;

        private String endpoint;

        public AKRestClientBuilder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public AKRestClientBuilder endpoint(Config config) {
            this.config = config;
            return this;
        }

        public String getAccessToken() throws Exception {
            AKRestClient akRestClient = new AKRestClient(endpoint, config);
            Result accessToken = akRestClient.getAccessToken(Config.APPID, Config.SEID);
            HashMap data = (HashMap) accessToken.getData();

            Object cooess_token = data.get("access_token");
            return data==null? accessToken.getMsg() :String.valueOf(cooess_token);
        }

        public Object refreshToken() throws Exception {
            AKRestClient akRestClient = new AKRestClient(endpoint, config);
            return akRestClient.refreshToken(Config.APPID, Config.SEID);
        }


        public Result getAccessToken(String appId, String appSecret) throws Exception {
            AKRestClient akRestClient = new AKRestClient(endpoint, config);
            return akRestClient.getAccessToken(appId, appSecret);
        }

        public Object refreshToken(String appId, String refreshToken) throws Exception {
            AKRestClient akRestClient = new AKRestClient(endpoint, config);
            return akRestClient.refreshToken(appId, refreshToken);
        }

        public Object sign(Map<String, Object> params) throws Exception {
            AKRestClient akRestClient = new AKRestClient(endpoint, config);
            return akRestClient.sign(params);
        }
    }

}
