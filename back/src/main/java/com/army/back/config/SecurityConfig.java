package com.army.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // CSRF 비활성화 (JWT 사용 시 필요)
            .httpBasic().disable()  // httpBasic 비활성화 (JWT 사용 시 필요)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/signup", "/", "/login").permitAll()  // 회원가입, 로그인 등은 누구나 접근 가능
                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
            )
            .formLogin(form -> form
                .loginPage("/loginform")  // 로그인 페이지 URL
                .loginProcessingUrl("/login")  // 로그인 처리 URL
                .defaultSuccessUrl("/")  // 로그인 성공 후 리다이렉트될 URL
                .permitAll()  // 로그인 관련 URL은 누구나 접근 가능
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // 로그아웃 URL
                .logoutSuccessUrl("/")  // 로그아웃 성공 후 리다이렉트될 URL
                .invalidateHttpSession(true)  // 세션 무효화
            )
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // JWT 사용 시 세션을 사용하지 않도록 설정
            );
        return http.build();
    }

}
