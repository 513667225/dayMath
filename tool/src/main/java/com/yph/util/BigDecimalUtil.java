package com.yph.util;

import com.yph.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Agu
 */
public class BigDecimalUtil {



    public  static BigDecimal multiply(BigDecimal b1,BigDecimal b2){
        if (b1 ==null || b2 == null){
            return new BigDecimal("0");
        }
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP);
         }catch (ArithmeticException e){
        //小于0
            bigDecimal = new BigDecimal("0");
    }
        return bigDecimal;
    }


    public static BigDecimal divide100(Integer size){
        BigDecimal bigDecimal=new BigDecimal("100");
        BigDecimal bigDecimal1 = new BigDecimal(size + "");
        BigDecimal divide1 = bigDecimal1.divide(bigDecimal);
        return divide1;
    }



    public static BigDecimal multiply100(Integer size){
        BigDecimal bigDecimal=new BigDecimal("100");
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(size + ""));
        return multiply;
    }

    public static BigDecimal divide100(String size){
        BigDecimal bigDecimal=new BigDecimal("100");
        BigDecimal bigDecimal1 = new BigDecimal(size);
        BigDecimal divide1 = bigDecimal1.divide(bigDecimal);
        return divide1;
    }


    public static BigDecimal multiply100(String size){
        BigDecimal bigDecimal=new BigDecimal("100");
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(size));
        return multiply;
    }


    public static BigDecimal divide10000000(String size){
        BigDecimal bigDecimal=new BigDecimal("10000000");
        BigDecimal bigDecimal1 = new BigDecimal(size);
        BigDecimal divide1 = bigDecimal1.divide(bigDecimal);
        return divide1;
    }


    public static BigDecimal multiply10000000(String size){

        return multiply10000000(new BigDecimal(size));
    }

    public static BigDecimal multiply10000000(BigDecimal size){
        BigDecimal bigDecimal=new BigDecimal("10000000");
        BigDecimal multiply = bigDecimal.multiply(size);
        return multiply;
    }


    public  static  BigDecimal[] multiply28(Long l,BigDecimal ticker){
        return multiply28(new BigDecimal(l.toString()),ticker);
    }


    /**
     * 28分润
     *  bigDecimals[0]是8 bigDecimals[1]是2
     * @param bigDecimal
     * @return
     */
    public  static  BigDecimal[] multiply28(BigDecimal bigDecimal,BigDecimal ticker){
        BigDecimal[] bigDecimals = new BigDecimal[3];
        BigDecimal old8 = bigDecimal.multiply(SystemConstant.rewardRate);
        BigDecimal new8 = old8.setScale(0, BigDecimal.ROUND_HALF_UP);
        boolean is4 = false;
        bigDecimals[0] = old8;
        if (new8.doubleValue()>old8.doubleValue()){
            bigDecimals[0] = new8;
            is4 = true;
        }

        BigDecimal old2 = bigDecimal.subtract(bigDecimals[0]);
        BigDecimal en2 = old2;
        //将2的部分转化为钱计算
        old2 = old2.multiply(ticker);
        BigDecimal new2 = old2.setScale(0, BigDecimal.ROUND_HALF_UP);
        bigDecimals[1] = old2;
        bigDecimals[2] = en2;
        if (!is4){
            bigDecimals[1] = new2;
            bigDecimals[2] = en2.setScale(0,BigDecimal.ROUND_HALF_UP);
        }

        return bigDecimals;
    }



}
