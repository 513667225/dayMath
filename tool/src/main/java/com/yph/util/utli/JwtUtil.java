package com.yph.util.utli;

import com.yph.enun.TipMsgEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.net.util.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    //解密的密钥 加密的时候传入密钥 解密的时候使用密钥来解析
    public static final String JWT_KEY = "YUANPINGHUI_QWERTY";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKeySpec generalKey() {
        String stringKey = JWT_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        return key;
    }

    public static Map<String, Object> generalKeyMap() {

        String stringKey = JWT_KEY;
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        Map<String, Object> map = new HashMap();
        map.put("arg1", encodedKey);
        map.put("arg2", encodedKey.length);
        map.put("arg3", "AES");
        return map;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis 过期的时间长度
     * @return
     * @throws Exception
     */
    public static String createJWT(String id, String subject) throws Exception {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
            long nowMillis = System.currentTimeMillis();//生成JWT的时间
            Date now = new Date(nowMillis);
            SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
            JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
//                .setClaims(claims)            //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                    .setId(id)                    //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                    .setIssuedAt(now)            //iat: jwt的签发时间
                    .setSubject(subject)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                    .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);        //设置过期时间
//        }
            return builder.compact();            //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
        }catch (Exception e){
           throw  new Exception(TipMsgEnum.TOKEN_CREATE_Excption.getMsg());
        }
    }


    public static void main(String[] args) throws Exception {
//        Map<String,Object> user = new HashMap<>();
//        user.put("userId","1048");
//        user.put("password","321");
        // {userId: 1048}
        //把数据转换为JWT token
        String jwt = createJWT(UUID.randomUUID().toString(), "1T8C0clzNP0P6xXt9oVceIVQrBRmLf");
//        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5N2FmZjE5Yy0xODk4LTRiNGMtYjQ1MC1kMTIyMDhlOTFhODciLCJpYXQiOjE1OTkwMzEwODcsInN1YiI6IntcImFkZFRpbWVcIjoxNTk4MDcwOTAzMDAwLFwiYmFsYW5jZVwiOjIuNTIsXCJkZWNsaW5lQ291bnRcIjowLFwiZGVjbGluZU51bWJlclwiOjAsXCJmcmVlemVfd2l0aGRyYXdfYmFsYW5jZVwiOjAuMCxcImZyZWV6ZV93aXRoZHJhd19tb25leVwiOjAuMCxcImZyZWV6ZW1vbmV5XCI6MC4wLFwiZ291cFNpemVcIjoxNDcsXCJpbnRlZ3JhbFwiOjAuMCxcIm1ham9yZG9tb1NpemVcIjowLFwibWFuYWdlclNpemVcIjowLFwibWVtYmVyU2l6ZVwiOjEsXCJyZWxhdGlvblwiOlwiMTA0MSwxMDQyLDEwNDMsMTA0NCwxMDYzLDE2OTQsMzAzNVwiLFwic2hvcHBpbmdfZ29sZFwiOjEwMS4wLFwic3BlbGxfYmVhblwiOjAuMTMsXCJ0ZWFtTWFqb3Jkb21vXCI6MCxcInRlYW1NZW1iZXJcIjoyLFwidGVhbW1hbmFnZXJTaXplXCI6MCxcInRvcFJlZmVyZWVJZFwiOjEwNDEsXCJ1bmRlcmxpbmdTaXplXCI6MjMsXCJ1cGRhdGVUaW1lXCI6MTU5ODA3MDkwMzAwMCxcInVzZXJBdmF0YXJcIjpcImh0dHBzOi8vdGhpcmR3eC5xbG9nby5jbi9tbW9wZW4vdmlfMzIvUTBqNFR3R1RmVEk0S3h4MDNydWt6NU54WGczc1ZqMVZ1Y085S0dhbnN3YmpKbDl1Und6c2dLQmhGUGdNODQzeUJqSko0ZXd1TlpMWW93dzNnMEkzMHcvMTMyXCIsXCJ1c2VyR2VuZGVyXCI6MCxcInVzZXJJZFwiOjQ1MDksXCJ1c2VyTGFzdExvZ2luSXBcIjpcIlwiLFwidXNlckxhc3RMb2dpblRpbWVcIjoxNTk4MDcwOTAzMDAwLFwidXNlckxldmVsXCI6MixcInVzZXJNb2JpbGVcIjpcIjEzOTA4NDg4MjczXCIsXCJ1c2VyTmlja25hbWVcIjpcIk1yLueOi1wiLFwidXNlclBhc3N3b3JkXCI6XCJcIixcInVzZXJSZWZlcnJlclwiOjMwMzUsXCJ1c2VyU3RhdHVzXCI6MH0ifQ.1MVN9P3a8xwmCdy3DvD6cdy_QLDoA5D8CGnrUp2f3Pk";
        System.out.println("加密后的"+jwt);
        //解密
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNzI1ZDUzNC02ODQ1LTQzMjctYmE1NS1lZDBlMTc1ZGY2NWQiLCJpYXQiOjE2Mjg1MDIyMDUsInN1YiI6IntcInVzZXJFbnRpdHlcIjp7XCJ1c2VySWRcIjo1OCxcInVzZXJuYW1lXCI6XCJ3ZWJjb3JlQGJsdWV5b25kZXIuY28udWtcIixcInBhc3N3b3JkXCI6XCIxMjM0NTZcIixcInR5cGVcIjoyLFwibGV2ZWxcIjoxLFwiY3JlYXRldGltZVwiOlwiMjAyMS0wNi0yOSAxNTozOTozOVwifSxcImJ1eWVyRW50aXR5XCI6e1wiYnV5ZXJJZFwiOjMzOTgsXCJjb3VudHJ5SWRcIjoyLFwibmFtZVwiOlwiRGFuaWVsIERhdmluY2lcIixcImxldmVsXCI6NDM3LFwiaW1nQ29tbWlzc2lvblwiOjAuMDAsXCJ3b3JkQ29tbWlzc2lvblwiOjAuMDAsXCJ2aWRlb0NvbW1pc3Npb25cIjowLjAwLFwiY29udGFjdERldGFpbHNcIjpcIndlYmNvcmVAYmx1ZXlvbmRlci5jby51a1wiLFwidXNlcklkXCI6NTgsXCJwcm9maWxlXCI6XCJEYW5pZWwgRGF2aW5jaVwiLFwiY29udGFjdERldGFpbHMyXCI6bnVsbCxcImZhY2Vib29rXCI6bnVsbCxcIndlQ2hhdFwiOm51bGwsXCJ0ZWxlZ3JhbVwiOm51bGwsXCJwYXlwYWxcIjpcIndlYmNvcmVAYmx1ZXlvbmRlci5jby51a1wiLFwicGF5b25lZXJcIjpudWxsLFwicmVtYXJrXCI6XCLpooTku5hcIn0sXCJzaG9wRW50aXR5XCI6bnVsbH0ifQ.YvtiexVe2Wsilf75hzxjZfUwlUVu7R6amM9xQBIyEis");

        System.out.println("解密后的"+claims.getSubject());
    }



    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        try {
            SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
            Claims claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
            return claims;
        }catch (Exception e){
            throw new Exception(TipMsgEnum.TOKEN_OF_Excption.getMsg());
        }
    }





}