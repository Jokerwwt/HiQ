package com.hiqgroup.hiq.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private JwtConfig jwtConfig ;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        /** 地址过滤 */
        String uri = request.getRequestURI() ;
        if (uri.contains("/login") || uri.contains("/getRegDeptsTree") || uri.contains("/getRegCompanys") || uri.contains("/error") || uri.contains("/showDaily") ||
                uri.contains("/downloadTemplateFile") || uri.contains("/regist") || uri.contains("/getDailyList") || uri.contains("/getDailysCount") ||
                uri.contains("/getKeyWordDailyList") || uri.contains("/getKeyWordDailysCount") ||
                uri.contains("/lucene/creat") || uri.contains("/kindEditor/getPicture") || uri.contains("/upload/getFile") || uri.contains("/upload/getTemplateFile") ||
                uri.contains("/showAbout") || uri.contains("/showIntroduction") || uri.contains("/showProductService") || uri.contains("/smtCompany/getCompanyTotal") ||
                uri.contains("/hpgDailytype/queryAll") || uri.contains("/hpgLinks/getLinkList") || uri.contains("/getReshowDailies") || uri.contains("/getIsBlackWhite")){
            return true ;
        }
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        /** Token 验证 */
        String headToken = request.getHeader(jwtConfig.getHeader());
        if(headToken == null || headToken.trim().equals("") || headToken.trim().equals("null")){
            headToken = request.getParameter(jwtConfig.getHeader());
        }
        if(headToken == null || headToken.trim().equals("")){
            System.out.println(jwtConfig.getHeader()+ "不能为空");
            return false;
        }
        Claims claims = null;
        try{
            claims = jwtConfig.getTokenClaim(headToken);
            Date expri = claims.getExpiration();
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                System.out.println(jwtConfig.getHeader() + "过期，请重新登录。");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                Result res = ResultUtil.error(ResultEnum.TOKEN_IS_INVALID.getCode(),"token过期，请重新登录。");
                String resStr = new Gson().toJson(res);
                PrintWriter out = null ;
                out = response.getWriter();
                out.write(resStr);
                out.flush();
                out.close();
                return false;
            }
        }catch (Exception e){
            System.out.println(jwtConfig.getHeader() + "失效，请重新登录。");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            Result res = ResultUtil.error(ResultEnum.TOKEN_IS_INVALID.getCode(),"token失效，请重新登录。");
            String resStr = new Gson().toJson(res);
            PrintWriter out = null ;
            out = response.getWriter();
            out.write(resStr);
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
//        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        vary: Origin
//        Vary: Access-Control-Request-Method
//        Vary: Access-Control-Request-Headers
//        Access-Control-Allow-Origin: *
    }
}
