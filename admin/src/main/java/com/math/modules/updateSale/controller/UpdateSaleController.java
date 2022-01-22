package com.math.modules.updateSale.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.updateSale.service.IUpdateSaleService;
import com.yph.annotation.Pmap;
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
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/updateSale")
public class UpdateSaleController {



    @Autowired
    IUpdateSaleService service;

    @RequestMapping("/list")
    public R page(@Pmap P p){
        Page tPage = new Page(p.getInt("page"), p.getInt("limit"));
        p.removePageParam();
        QueryWrapper like = new QueryWrapper();
        String asin = p.getString("asin");
        if (asin!=null){
            like.eq("asin",asin);
        }
        String site = p.getString("site");
        if (site!=null){
            like.eq("site",site);
        }
        like.orderByDesc("update_id");
        service.page(tPage,like);
        return R.success().data(tPage.getRecords()).set("total",tPage.getTotal());
    }
}
