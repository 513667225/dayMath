package com.math.modules.seller.controller;


import com.math.modules.seller.entity.SellerEntity;
import com.math.modules.seller.service.ISellerService;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    ISellerService sellerService;

    @RequestMapping("/getAllWname")
    public R getAllWname(){
        List<String> list = new ArrayList<>();
        for (SellerEntity sellerEntity : sellerService.list()) {
            String str = sellerEntity.getName()+sellerEntity.getCountry()+"仓";
            list.add(str);
        }
        return R.success().data(list);
    }



    @RequestMapping("/getAllSeller")
    public  R getAllSeller(){

        return R.success().data(sellerService.list());

    }



}
