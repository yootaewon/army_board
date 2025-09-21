package com.army.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 엔드포인트에 대해 CORS 허용
        registry.addMapping("/**")  // 모든 엔드포인트에 대해 CORS 허용
                .allowedOrigins("http://localhost:3000")  // React 앱의 주소 (서버 주소 변경 시 이 부분 수정)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 필요한 HTTP 메소드 허용
                .allowedHeaders("*")  // 모든 헤더 허용
                .allowCredentials(true);  // 쿠키와 인증 정보 포함 허용 (필요한 경우)
    }

}
