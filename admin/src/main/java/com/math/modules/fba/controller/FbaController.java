package com.math.modules.fba.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.fba.service.IFbaService;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.math.modules.fbaStore.service.IFbaStoreService;
import com.math.modules.sale.service.ISaleService;
import com.math.modules.saleSum.service.ISaleSumService;
import com.math.sdk.samples.OrderDemo;
import com.yph.annotation.Pmap;
import com.yph.util.DateUtil;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fba")
public class FbaController {


    @Autowired
    IFbaProductService fbaProductService;

    @Autowired
    IFbaStoreService fbaStoreService;

    @Autowired
    ISaleSumService saleSumService;


    @RequestMapping("/fbaStock/fbaList")
    public R fbaList(@Pmap P p) {
        return fbaStoreService.queryStorePage(p);

    }

    @RequestMapping("/data/fba_report/shipmentList")
    public R shipmentList(@Pmap P p) throws Exception{

        return fbaProductService.queryProductPage(p);
    }




    @RequestMapping("/data/sales_report/sales")
    public R sales(@Pmap P p) throws Exception{

        return saleSumService.querySalePage(p);
    }


    @Autowired
    IFbaService fbaService;

    @RequestMapping("/syncSale")
    public  R syncSale() throws Exception{
        fbaService.syncSale();
        return R.success();
    }


    @RequestMapping("/syncFbaStore")
    public  R syncFbaStore() throws Exception{

        fbaService.syncFbaStore();
        return R.success();
    }




    @RequestMapping("/syncFbaProduct")
    public  R syncFbaProduct() throws Exception{

        fbaService.syncFbaProduct();
        return R.success();
    }

    @RequestMapping("/syncListing")
    public  R syncListing() throws Exception{
        fbaService.syncListing();

        return R.success();
    }

    @RequestMapping("/syncAccount")
    public  R syncAccount() throws Exception{
        fbaService.syncAccount();

        return R.success();
    }



    @RequestMapping("syncSeller")
    public  R syncSeller() throws Exception{

        fbaService.syncSeller();
        return R.success();
    }


    @Autowired
    ISaleService saleService;


    @RequestMapping("/testEu")
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
