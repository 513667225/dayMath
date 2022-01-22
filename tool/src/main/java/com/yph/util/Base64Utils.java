package com.yph.util;

import java.util.Base64;

public class Base64Utils {

    private static final Base64.Decoder BASE_64_DECODER = Base64.getDecoder();

    private static final Base64.Encoder BASE_64_ENCODER = Base64.getEncoder();

    public static byte[] decode(String str) {
        return BASE_64_DECODER.decode(str);
    }

    public static byte[] decode(byte[] bytes) {
        return BASE_64_DECODER.decode(bytes);
    }

    public static byte[] decodeString(byte[] bytes) {
        return BASE_64_DECODER.decode(bytes);
    }

    public static String encodeToString(byte[] bytes) {
        return BASE_64_ENCODER.encodeToString(bytes);
    }

    public static byte[] encode(byte[] bytes) {
        return BASE_64_ENCODER.encode(bytes);
    }

    public static byte[] encode(String str) {
        return BASE_64_ENCODER.encode(str.getBytes());
    }

}
