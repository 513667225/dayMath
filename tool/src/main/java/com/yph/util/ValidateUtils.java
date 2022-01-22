package com.yph.util;

public class ValidateUtils {

    private static final int[] ID_CARD_W = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    private static final char[] ID_CARD_CODE = "10X98765432".toCharArray();

    public static boolean isCard(String id) {
        if (id == null || id.length() < 18) {
            return false;
        }
        char[] chars = id.toCharArray();
        int sum = 0;
        for (int i = 0; i < ID_CARD_W.length; i++) {
            sum += (chars[i] - '0') * ID_CARD_W[i];
        }
        char ch = ID_CARD_CODE[sum % 11];
        return chars[17] == ch;
    }

    public static boolean isBankCard(String bankCard) {
        if (bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0 || !nonCheckCodeBankCard.matches("\\d+")) {
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int sum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            sum += k;
        }
        return (sum % 10 == 0) ? '0' : (char) ((10 - sum % 10) + '0');
    }

}
