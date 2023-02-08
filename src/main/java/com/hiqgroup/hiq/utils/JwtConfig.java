package com.hiqgroup.hiq.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT的token，区分大小写
 */
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param claims
     * @return
     */
    public String createToken (Map<String, Object> claims){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 60 * 1000);//过期时间
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setClaims(claims)
                .setId(claims.get("id").toString())  //登录用户的id
                .setAudience(claims.get("userid").toString())
                .setSubject(claims.get("username").toString())  //登录用户的名称
                .setIssuedAt(nowDate)//当前时间
                .setExpiration(expireDate)//过期时间
                .signWith(key)//头部信息 第一个参数为加密方式为哈希512  第二个参数为加的盐为secret字符串
                .compact();
    }

    public Map getNewToken(){
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String headToken = request.getHeader(getHeader());
        Map map = new HashMap<>();
        if(headToken != null && !headToken.trim().equals("") && !headToken.trim().equals("null")) {
            Claims claims = getTokenClaim(headToken);
            map.put("id", claims.getId());
            map.put("userid", claims.getAudience());
            map.put("username", claims.getSubject());
            map.put("usercompany", claims.get("usercompany").toString());
            map.put("usercompanytype", claims.get("usercompanytype").toString());
            map.put("userdept", claims.get("userdept").toString());
            String newToken = createToken(map);
            map.put("token", createToken(map));
        }
        return map;
    }

    public Map getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String headToken = request.getHeader(getHeader());
        Map map = new HashMap<>();
        if(headToken != null && !headToken.trim().equals("") && !headToken.trim().equals("null")) {
            Claims claims = getTokenClaim(headToken);
            map.put("id", claims.getId());
            map.put("userid", claims.getAudience());
            map.put("username", claims.getSubject());
            map.put("usercompany", claims.get("usercompany").toString());
            map.put("usercompanytype", claims.get("usercompanytype").toString());
            map.put("userdept", claims.get("userdept").toString());
        }
        return map;
    }

    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }


    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
