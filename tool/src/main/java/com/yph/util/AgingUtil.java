package com.yph.util;

import com.yph.entity.AgingEntity;

public class AgingUtil {



    public  static String type(Integer day){
        if (day<=0){
            return "断货快递";
        }
        if (day<20){
            return "快递";
        }
        if (day<40){
            return  "空运";
        }
        return "海运";
    }




    public  static  Integer needInDay(AgingEntity agingEntity,String type){
        switch (type){
            case "海运":

                return  agingEntity.getSeaDay();
            case "空运":

                return  agingEntity.getSeaDay();
            case "快递":
            case "断货快递":
                return  agingEntity.getAirDay();
        }
       return agingEntity.getExpressDay();
    }


    public  static  String nextType(String type){
        switch (type){
            case "空运":
                return "海运";
            case "快递":
            case "断货快递":
                return "空运";
        }
        return "海运";
    }

    public  static  Integer getEntityDay(AgingEntity agingEntity,String type){
            switch (type){
                case "海运":

                   return  agingEntity.getSeaDay();
                case "空运":

                   return  agingEntity.getAirDay();
                case "快递":
                case "断货快递":
                    return  agingEntity.getExpressDay();
            }
        return  agingEntity.getExpressDay();
    }

}
