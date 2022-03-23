package com.demo.provider.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/20
 * Time: 17:24
 * Description: 解决跨域
 */
@Configuration
public class MyCorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*")
//                        .allowedOriginPatterns("*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}
