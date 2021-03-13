package com.mssm.utils;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "misssimple";

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        //定义jwt签名的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //将当前时间转换日期类型
        Date now = new Date(nowMillis);
        //将当前时间+超时时间
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        //将时间定义为date类型
        Date expDate = new Date(expMillis);
        //获取签名时候使用的密钥
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("admin")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .setExpiration(expDate)// 设置过期时间
                .signWith(signatureAlgorithm, secretKey); //使用HS256对称加密算法签名, 第二个参数为秘钥
        String compact = builder.compact();
        return compact;

        //解密jwd令牌
        //Claims william = Jwts.parser().setSigningKey("misssimple").parseClaimsJws(jwt).getBody();
        //System.out.println(william);
    }

    // 验证
    public static Boolean verify(String jwt){
        try{
            //获取签名时候使用的密钥
            SecretKey secretKey = generalKey();
            Claims william = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            System.out.println("解密jwd令牌:"+william);
            return true;
        } catch (Exception e){
            System.out.println("解密失败");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
