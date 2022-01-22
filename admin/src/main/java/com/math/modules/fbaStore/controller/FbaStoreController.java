package com.math.modules.fbaStore.controller;


import com.math.modules.fbaStore.service.IFbaStoreService;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/fbaStore")
public class FbaStoreController {


    @Autowired
    IFbaStoreService fbaStoreService;

    @RequestMapping("/getAll")
    public R getAll(){

        return fbaStoreService.getAllName();
    }



    @RequestMapping("/list")
    public R list(P p){

        return fbaStoreService.queryStorePage(p);
    }
}
