package com.math.modules.aging.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.aging.service.IAgingService;
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
@RequestMapping("/aging")
public class AgingController {

    @Autowired
    IAgingService service;


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
        like.orderByDesc("aging_id");
        service.page(tPage,like);
        return R.success().data(tPage.getRecords()).set("total",tPage.getTotal());
    }



    @RequestMapping("/del")
    public  R del(@Pmap P p){
        Integer id = p.getInt("id");
        service.removeById(id);
        return R.success();
    }

    @RequestMapping("/uploadInfo")
    public  R uploadInfo(@Pmap P p, MultipartFile file) throws Exception{

        return service.upload(file);
    }

}
