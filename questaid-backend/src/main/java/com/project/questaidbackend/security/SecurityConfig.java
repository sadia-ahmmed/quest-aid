package com.project.questaidbackend.security;

import com.project.questaidbackend.security.filter.AuthenticationFilter;
import com.project.questaidbackend.security.filter.ExceptionHandlerFilter;
import com.project.questaidbackend.security.filter.JWTAuthorizationFilter;
import com.project.questaidbackend.security.manager.CustomAuthManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private CustomAuthManager customAuthManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter(customAuthManager);
        authFilter.setFilterProcessesUrl("/authenticate");

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                .requestMatchers(HttpMethod.GET, SecurityConstants.PUBLIC_GET_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();
    }

}
