package com.yph.util.utli;


import com.yph.entity.DetailDo;
import com.yph.entity.SpellBillDo;
import com.yph.util.DateUtil;
import com.yph.util.FigureUtli;

public class BuildDetailDoUtil {

    public static DetailDo build(String did,int userKey, int operationType, int money_type, int raw_user_id, int to_user_id, double money_number,String serial_number,String explain,String rests,int marking){
        return new DetailDo(
                did,
                userKey,
                operationType,
                money_type,
                raw_user_id,
                to_user_id,
                FigureUtli.transferMinute(money_number),
                DateUtil.getDateString(),
                serial_number,
                explain,
                rests,
                marking
        );
    }


    public  static SpellBillDo buildSpellBillDo(String groupId, String ren, Integer groupSize){
        return  new SpellBillDo(
                groupId
                ,ren
                , groupSize
               , DateUtil.getDateString()
        );
    }

}
