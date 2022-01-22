package com.math.modules.size.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.size.entity.SizeEntity;
import com.math.modules.size.service.ISizeService;
import com.yph.annotation.Pmap;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/size")
public class SizeController {


    @Autowired
    ISizeService service;

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
        like.orderByDesc("size_id");
        service.page(tPage,like);
        return R.success().data(tPage.getRecords()).set("total",tPage.getTotal());
    }


    @RequestMapping("/update")
    public  R update(@Pmap P p) throws Exception{

        service.update(p);
        return R.success();
    }

    @RequestMapping("/del")
    public  R del(@Pmap P p){
        Integer id = p.getInt("id");
        service.removeById(id);
        return R.success();
    }


    @RequestMapping("/getById")
    public  R getById(@Pmap P p){


        return  R.success().data(service.getById(p.getInt("id")));
    }

    @RequestMapping("/uploadInfo")
    public  R uploadInfo(@Pmap P p, MultipartFile file) throws Exception{

        return service.upload(file);
    }
}
