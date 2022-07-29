package com.yantailor.turing_web_re.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;


public class JwtUtil {

    public static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 过期时间两小时
    private static final long ACCESS_TOKEN = 1000*60*60*2;

    // 过期时间八小时
    private static final long REFRESH_TOKEN = 1000*60*60*8;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String email, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("email", email)
//                    .withClaim("role", role)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    public static String getRole(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("role").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
    * 获得token中的信息无需secret解密也能获得
    * @return token中包含的邮箱
    * */
    public static String getEmail(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("email").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 生成签名,2h后过期
     * @param email 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String email, String secret, Collection<? extends GrantedAuthority> role) {
        try {
            Date date = new Date(System.currentTimeMillis()+ACCESS_TOKEN);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("email", email)
                    .withClaim("role", role.toString())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    /**
     * 刷新签名,8h后过期
     * @param email 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String signRefresh(String email, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+REFRESH_TOKEN);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("email", email)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证token 是否过期(true:已过期 false:未过期)
     */
    public static Boolean  isTokenExpired(String token){
        long res = 0;
        try {
            res = 0;
            DecodedJWT jwt = JWT.decode(token);
            Date expiresAt = jwt.getExpiresAt();
            return expiresAt.before(expiresAt);
        } catch (JWTDecodeException e) {
            log.error("error={}", e);
            return true;
        }
    }

    /**
     * 验证token是否有效 (true：验证通过 false：验证失败)
     */
    public static Boolean validateToken(String token) {
       return getEmail(token) != null && !isTokenExpired(token);
    }


    /**
     * 获取token的剩余过期时间
     */
    public static long getRemainingTime(String token) {
        long res = 0;
        try {
            long nowMillis = System.currentTimeMillis();

            //剩余过期时间 = token的过期时间-当前时间
            res = JWT.decode(token).getExpiresAt().getTime() - nowMillis;
        } catch (Exception e) {
            log.error("error={}", e);
        }
        return res;
    }





}
