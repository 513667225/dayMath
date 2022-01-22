package com.math.modules.arrival.controller;


import com.math.modules.arrival.service.IArrivalService;
import com.math.modules.fbaProduct.service.IFbaProductService;
import com.yph.annotation.Pmap;
import com.yph.util.P;
import com.yph.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/arrival")
public class ArrivalController {


    @Autowired
    IArrivalService arrivalService;

    @Autowired
    IFbaProductService fbaProductService;


    @RequestMapping("/save")
    public R save(@Pmap P p) throws Exception {

        return arrivalService.save(p);
    }

}
