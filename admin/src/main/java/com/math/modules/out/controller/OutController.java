package com.math.modules.out.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.math.modules.out.service.IOutService;
import com.yph.annotation.Pmap;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/out")
public class OutController {

    @Autowired
    IOutService service;

    @RequestMapping("/list")
    public R page(@Pmap P p){
        Page tPage = new Page(p.getInt("page"), p.getInt("limit"));
        p.removePageParam();
        QueryWrapper like = new QueryWrapper();
        String times = p.getSqlDateByString("times");
        if (times!=null){
            like.like("times",times);
        }
        String asin = p.getString("asin");
        if (asin!=null){
            like.eq("asin",asin);
        }

        String user = p.getString("user");
        if (user!=null){
            like.eq("user",user);
        }
        String country = p.getString("country");
        if (country!=null){
            like.like("country",country);
        }
        like.orderByDesc("out_id");
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

        return service.uploadOut(file);
    }




    @RequestMapping("/downloadFile")
    @ResponseBody
    private String downloadFile(@Pmap P p){
        HttpServletResponse response = p.getResponse();
        String fileName = p.getString("fileName");
        File file = new File("d:/mach/excel/"+ fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName="+fileName );
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

                return "Download successfully";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "Download failed";
    }

}
