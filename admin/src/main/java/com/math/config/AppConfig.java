package com.math.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.math.modules.fba.service.IFbaService;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.in.service.IInService;
import com.math.modules.sale.service.ISaleService;
import com.yph.filter.LoginHtmlFilter;
import com.yph.util.JSONUtils;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * @author Agu
 */
@Configuration
public class AppConfig {

    @Autowired
    IInService inService;

    @Bean
    public LoginHtmlFilter loginHtmlFilter() {
        return new LoginHtmlFilter();
    }

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loginHtmlFilter());
        registration.addUrlPatterns("*.html");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Scheduled(cron = "0 0 7 * * ?")
    public void US() throws Exception{

//        inService.syncSize("欧洲");
    }



    @Autowired
    IFbaProductService fbaProductService;

    public  void flushSale(){
     saleService.flushSale();

    }

    @Autowired
    IFbaService fbaService;

    @Autowired
    ISaleService saleService;

    @Scheduled(cron = "0 0 16 * * ?")
    public void EU()throws Exception {
//        inService.syncSize("美国");
        boolean f = true;
        while (f){
            try {
                fbaService.syncSale();
            }catch (SocketTimeoutException e){
                continue;
            }
         f=false;
        }
        saleService.flush();

        f = true;
        while (f){
            try {
                fbaService.syncFbaProduct();
            }catch (SocketTimeoutException e){
                continue;
            }
            f=false;
        }
        f = true;
        while (f){
            try {
                fbaService.syncFbaStore();
            }catch (SocketTimeoutException e){
                continue;
            }
            f=false;
        }
    }






}
