package com.hiqgroup.hiq.utils;

import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HeaderModifierAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private JwtConfig jwtConfig ;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 转换对象
        String methodName = methodParameter.getMethod().getName();
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        // 获得token
        String headToken = request.getHeader(jwtConfig.getHeader());
        if(headToken != null && !headToken.trim().equals("") &&
                !headToken.trim().equals("null") && !methodName.equals("queryAllByLimit")
                && !methodName.equals("login")){
            Claims claims = jwtConfig.getTokenClaim(headToken);
            Map map = new HashMap<>();
            map.put("id", claims.getId());
            map.put("userid", claims.getAudience());
            map.put("username", claims.getSubject());
            map.put("usercompany", (claims.get("usercompany") == null)? "": claims.get("usercompany").toString());
            map.put("usercompanytype", (claims.get("usercompanytype") == null)? "": claims.get("usercompanytype").toString());
            map.put("userdept", (claims.get("userdept") == null)? "": claims.get("userdept").toString());
            String token = jwtConfig.createToken(map);
            if (StringUtils.isNotBlank(token)) {
                /*刷新token*/
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setHeader( "Access-Control-Expose-Headers" , "token,userid,username,usercompany,usercompanytype,userdept" );
                response.addHeader("token", token);
                response.addHeader("userid", map.get("id").toString());
                response.addHeader("username", URLEncoder.encode(map.get("username").toString(), "utf8"));
                response.addHeader("usercompany", map.get("usercompany").toString());
                response.addHeader("usercompanytype", map.get("usercompanytype").toString());
                response.addHeader("userdept", map.get("userdept").toString());
            }
        }
        return o;
    }
}
