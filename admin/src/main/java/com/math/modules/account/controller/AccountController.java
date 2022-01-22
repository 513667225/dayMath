package com.math.modules.account.controller;


import com.math.modules.account.service.IAccountService;
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
 * @since 2021-12-27
 */
@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    IAccountService service;

    @RequestMapping("/getAll")
    public R getAll(){

        return service.getAllName();
    }

}
