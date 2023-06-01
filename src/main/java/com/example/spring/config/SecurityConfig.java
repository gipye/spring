package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()   // Only for test, csrf token is disabled
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/signin");
        return http.build();
    }
}
