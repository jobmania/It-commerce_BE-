package com.sideproject.shop.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtSecurityConfig  {

    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public HttpSecurity filterChain(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
       return http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
