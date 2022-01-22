package com.yph.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    public final static String C_MINUS = "-";
    public final static String C_EMPTY = "";
    public final static Integer EMAILCODE_RANDOM_LEN = 6;
    public final static Integer NUM_CODE_ONLY = 0;
    public final static Integer CHAR_CODE_ONLY = 2;
    public final static Integer REMIX_CODE = 3;

    /**
     * 生成UUID
     * @param isSource 是否32位
     * @return
     */
    public static String generateUUID(boolean isSource) {
        return isSource ? UUID.randomUUID().toString().replace(C_MINUS, C_EMPTY) : UUID.randomUUID().toString();
    }

    public static String generate32UUID() {
        return generateUUID(true);
    }

    public static String generate36UUID() {
        return generateUUID(false);
    }

    /**
     * 随机产生字符串
     * @param length 字符串长度
     * @param type 类型 (0: 仅数字; 2:仅字符; 别的数字:数字和字符)
     * @return
     */
    public static String getRandomStr(int length, int type) {
        String str = "";
        int beginChar = 'a';
        int endChar = 'z';
        // 只有数字
        if (type == 0) {
            beginChar = 'z' + 1;
            endChar = 'z' + 10;
        }
        // 只有小写字母
        else if (type == 2) {
            beginChar = 'a';
            endChar = 'z';
        }
        // 有数字和字母
        else {
            beginChar = 'a';
            endChar = 'z' + 10;
        }

        // 生成随机类
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int tmp = (beginChar + random.nextInt(endChar - beginChar));
            // 大于'z'的是数字
            if (tmp > 'z') {
                tmp = '0' + (tmp - 'z');
            }
            str += (char) tmp;
        }
        return str;
    }

    /**
     * 邮件随机字符串验证码
     * @return
     */
    public static String generateEmailCodeStr() {
       return getRandomStr(EMAILCODE_RANDOM_LEN, REMIX_CODE);
    }
}
