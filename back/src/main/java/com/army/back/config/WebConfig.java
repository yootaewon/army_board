package com.army.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 엔드포인트에 대해 CORS 허용
        registry.addMapping("/api")
                .allowedOrigins("http://localhost:3000") // React 앱 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 필요한 HTTP 메소드 추가
                .allowedHeaders("*"); // 모든 헤더 허용
    }
}
