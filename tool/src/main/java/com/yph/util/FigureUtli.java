package com.yph.util;

import java.math.BigDecimal;

public class FigureUtli {

    public static Double transferMinute(double money){
        BigDecimal bigDecimal=new BigDecimal(money+"");
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100+"")).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.err.println(multiply.doubleValue()+"-------");
        return multiply.doubleValue();
    }


    public static Double transferMinuteDivide(double money){
        BigDecimal bigDecimal=new BigDecimal(money+"");
        BigDecimal multiply = bigDecimal.divide(new BigDecimal(100+"")).setScale(2, BigDecimal.ROUND_DOWN);
        return multiply.doubleValue();
    }

}
