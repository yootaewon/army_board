package com.army.back.config;

import com.army.back.jwt.JwtSecurityConfig;
import com.army.back.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor  
public class SecurityConfig {

    private final TokenProvider tokenProvider;  

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (JWT 사용 시 필요)
                .httpBasic(basic -> basic.disable())  // httpBasic 비활성화 (JWT 사용 시 필요)
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/api/signUp", "/api/signIn").permitAll()  // 회원가입, 로그인 등은 누구나 접근 가능
                                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")  // 로그아웃 URL
                                .logoutSuccessUrl("/")  // 로그아웃 성공 후 리다이렉트될 URL
                                .invalidateHttpSession(true)  // 세션 무효화
                )
                .sessionManagement(sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // JWT 사용 시 세션을 사용하지 않도록 설정
                )
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
