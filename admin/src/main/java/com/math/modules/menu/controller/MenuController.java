package com.math.modules.menu.controller;


import com.math.modules.menu.service.IMenuService;
import com.yph.annotation.Pmap;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @RequestMapping("/getMenu")
    public R getMenu(@Pmap P p) throws Exception{

        return menuService.getMenu(p);
    }

    @RequestMapping("/getHomeUrl")
    public R getHomeUrl(@Pmap P p){

//        UserDo userDo = p.getUserDo();
//        if (userDo==null){
//            return R.error("当前未登录");
//        }
//        String name = "home";
//        if (userDo.getBuyerEntity()!=null){
//            name = "recommendations";
//        }
//        return R.success("success", HomeUrlEnum.getUrlByLevel(userDo.getUserEntity().getLevel())).set("name",name);

            return R.success();
    }

    @RequestMapping("/getOrder")
    public R getOrder(@RequestParam Map<String,Object> map ) throws Exception{

        return menuService.getOrder(map);
    }



}
