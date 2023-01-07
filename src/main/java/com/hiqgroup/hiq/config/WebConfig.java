package com.hiqgroup.hiq.config;

import com.hiqgroup.hiq.utils.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor ;

    /**
     * 配置全站允许CORS跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //允许的来源域  如http://www.demo.com
                .allowedMethods("GET", "POST","PUT","DELETE","PATCH")
                .allowCredentials(false).maxAge(3600);
    }
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/fileUpdataApi/upload","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        // registry.addResourceHandler("/attached/**").addResourceLocations("file:D:/img/attached/");
        registry.addResourceHandler("/attached/**").addResourceLocations("file:/var/local/attached/");
    }
}
