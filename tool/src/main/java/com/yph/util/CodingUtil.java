package com.yph.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 目前能用到的编码转化,加密解密，签名工具类
 * 目前支：Base64,AES，RSA
 * @author Agu
 */
public class CodingUtil {

    public static class Aes {

        private static final String ALGORITHM = "AES";
        /**
         * "算法/模式/补码方式"
         */
        private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";

        public static String generateAESKey() throws Exception {
            //KeyGenerator提供对称密钥生成器的功能，支持各种算法
            KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
            //SecretKey负责保存对称密钥
            keygen.init(128);
            String key = org.apache.commons.codec.binary.Base64.encodeBase64String(keygen.generateKey().getEncoded());
            return key;
        }

        /**
         * AES ecb加密
         *
         * @param content
         * @param key
         * @return
         * @throws Exception
         */
        public static String encryptECB(String content, String key) throws Exception {
            SecretKeySpec keySpec = new SecretKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(key.getBytes()), ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptBytes = cipher.doFinal(content.getBytes("UTF-8"));
            return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptBytes);
        }

        /**
         * AES ecb解密
         *
         * @param content 密文
         * @param key     aes秘钥
         * @return
         * @throws Exception
         */
        public static String decryptECB(String content, String key) throws Exception {
            SecretKeySpec keySpec = new SecretKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(key.getBytes()), ALGORITHM);
            Cipher deCipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            deCipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] bytes = deCipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(content.getBytes("UTF-8")));
            return new String(bytes, "UTF-8");
        }

        public static void main(String[] args) throws Exception {
            String key = generateAESKey();
//            System.out.println("key"+key);
            String plainText = "1wYQBghAeqoRQhzuw0Nvu1FhwsNP1ghuk3WU0/BeAhRUz2q3bZpLjosAFO4atEjMw9Xe0Er2wy3h\n" +
                    "4zRMKct9wtDMi/wg+QF/5aATcHdLkMVz2rjCuLbdM7uVP8f27d3kUg5cTnOC65Ol4dOlFaRjUbGr\n" +
                    "+DFJHKtTSQqCAiQxB+iFzguhuUJLaLrwC8lWg137yls/gCnplXlx5iVYnUT/xJM7qq53N2C6Dd9/\n" +
                    "Jc1K751IoooUGBkINtMO3DXuX7XmK2+RgWwSqRMNlCwn9KV2iVA8P7X+uvbrIiaCp9KzaE5+7gCC\n" +
                    "9XR/FtihnQBpgHVQSXu/e51BSb+XPT5R/sOjHZUlVqWzn8cat/JflcO2bhz88Q97fbD2/n2XcfPd\n" +
                    "fz1/p/ZEnimtGdb6E0YYLUFj3XU4IaTKmqnznTHnCq9stE5slo+hGGrv0Guc95D4ZBJJvZWI9WR0\n" +
                    "KRvda57K5gn2FuhCiM9qseqmeLdyI3+4/6gBJKGbCDj1d+OED6aGZdz9vWA36iget2DvgUW+IV5g\n" +
                    "N14ZfFj2skWYAKtw/uZMWCLCtlDi0K09/92pshJKVOQ3usuLKQgtpx3VkzMgCUbpSqF34O3zf+zl\n" +
                    "5CrxvIQ8bUhI0upPSUMSpp5Pkt8yuq/PnNmME7DlytumAsmSMpUUeuuymi7M3wdNrkVikVUkYl+c\n" +
                    "wtzlyjdhEwGiHLAz4sTIp9Qc7azS+DKIsFH/dkWRknWCGrZgz0ydvV1uo8twIKgMFS13TqQeF+nS\n" +
                    "ybMAKw5/jQVCAfov6c7QcAXhzgYpI8HyH4X63x53WC7XA7RD4cvWXbqBQXLsUapMo/oxodupn0V8\n" +
                    "b57+TIq9wapPby3KlsPoC5krqgqQlMgxjQzNvGxzyW0Cx+zxA/YfHIIjex1AwC1G3Q+mxVG1DFCn\n" +
                    "yP/pqzLbRA8q1fmNPUxin5sDZTbqiJUY880BMYL/tplL/eguT+CPCt6k871mdJ347pYDbm0N3K9p\n" +
                    "+0f6mXzDFUwVMJyjuPK53AXjktEc6qQItSi0CGa9isMlgywJN8xc0JLuTV0Vqz+7fDrjFZf4sCsD\n" +
                    "q1hSyCMTiYUrimiAuRLgcznHoc2qLWR+Tv4fMNmWv0bCCajUp8H+lMNmACrqAAei6WZlf7baC37H\n" +
                    "c3kQcsO0MyPU9ZIY6ga4M/7wuzSt73PzVRadrD+wi89Moo3bwHHGMj8C6GWwzqUbb76JX5tR8SPN\n" +
                    "iXTKn9sxcGd+pmw7U6XnKWd6/yCDjRG2y6JjV3m7yzErsM8Vc75fyqyQwPP3KFCsoEKi7XqCOOCo\n" +
                    "q5+5XIGOHSCq3aaL7hkBdtZdnGuzl2w=2018032820470240040";
            String encryp = encryptECB(plainText, key);
            System.out.println("encryp:" + encryp);
            System.out.println("================");
            System.out.println("plainText:" + decryptECB(encryp, key));
        }
    }

    public static class Base64Util {
        public static void main(String[] args) {
            //测试编码和解码
            final String text = "test";
            final String encodedText = Base64Util.encoder(text);
            //编码
            System.out.println(encodedText);
            //解码
            System.out.println(Base64Util.decoder(encodedText));
        }

        public static String encoder(String string) {
            return encoder(string, "UTF-8");
        }

        public static String encoder(String string, String charsetName) {
            try {
                byte[] bytes = string.getBytes(charsetName);
                return encoder(bytes);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String encoder(byte[] bytes) {
            return Base64.getEncoder().encodeToString(bytes);
        }


        public static String decoder(String string) {
            return decoder(string, "UTF-8");
        }

        public static String decoder(String string, String charsetName) {
            try {
                byte[] bytes = string.getBytes(charsetName);
                return decoder(bytes, charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String decoder(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
            return new String(Base64.getDecoder().decode(bytes), charsetName);
        }


    }



    public  static  class  Rsa {




    }



}
