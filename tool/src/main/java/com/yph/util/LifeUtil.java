package com.yph.util;

import java.math.BigDecimal;

/**
 * @author Agu
 */
public class LifeUtil {


    //校准生命源价值 N 1
    //今天的减昨天的除以昨天的 就是比率  +10+N*${0.1}  -10+N*${0.01}
    public static BigDecimal calibration(Long lifeSource, BigDecimal ticker) {
        BigDecimal bigDecimal = new BigDecimal(lifeSource.toString());
        return calibration(bigDecimal,ticker);
    }

    public static BigDecimal lifeFrom1013(Long ticker) {
            return lifeFrom1013(new BigDecimal(ticker.toString()));
    }

    public  static BigDecimal rmbFromLife(BigDecimal lifeSource,BigDecimal ticker){
        return div1013(lifeSource,getLifeRmbRate(ticker));
    }
    //ticker当前BPC兑RMB的比率
    public  static  BigDecimal getLifeRmbRate(BigDecimal ticker){
        BigDecimal multiply10 =new BigDecimal("10");
        BigDecimal multiply2 ;
        if (ticker.doubleValue() >= 1) {
            BigDecimal multiply = ticker.multiply(new BigDecimal("0.1"));
            multiply2 = multiply10.add(multiply);
        }else {
            BigDecimal multiply = ticker.multiply(new BigDecimal("0.01"));
            multiply2 = multiply10.subtract(multiply);
        }
        return multiply2==null? new BigDecimal("10") : multiply2;
    }


    //1013价值所需生命源
    public static BigDecimal lifeFrom1013(BigDecimal ticker) {

        return rmbFromLife(new BigDecimal("101300"),ticker);
    }

    public  static  BigDecimal div1013(BigDecimal div,BigDecimal life){

        return div.divide(life,2,BigDecimal.ROUND_HALF_UP);
    }



    public  static  BigDecimal lifeToEnergy(Long l,BigDecimal ticker){

        return lifeToEnergy(new BigDecimal(l.toString()),ticker);
    }



    /**
     * 生命源转化为能量源
     * @param l
     * @return
     */
    public  static  BigDecimal lifeToEnergy(BigDecimal l,BigDecimal ticker){
        BigDecimal calibration = calibration(l, ticker);
        return calibration.divide(ticker,2,BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calibration(BigDecimal bigDecimal, BigDecimal ticker) {
        return bigDecimal.multiply(getLifeRmbRate(ticker)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }


    public static void main(String[] args) {
//        System.out.println(rmbFromLife(new BigDecimal("100000"),new BigDecimal("2")));
        System.out.println(calibration(new BigDecimal("9794"),new BigDecimal("1.4")));
    }


}
