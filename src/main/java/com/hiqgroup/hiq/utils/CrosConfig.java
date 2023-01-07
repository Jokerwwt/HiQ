package com.hiqgroup.hiq.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH","OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("access-control-allow-headers", "access-control-allow-methods", "access-control-allow" +
                        "-origin", "access-control-max-age", "X-Frame-Options","Authorization")
                .maxAge(3600);
    }
}
