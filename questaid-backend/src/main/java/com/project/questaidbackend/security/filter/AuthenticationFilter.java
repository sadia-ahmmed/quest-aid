package com.project.questaidbackend.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.questaidbackend.models.base.GeneralResponseData;
import com.project.questaidbackend.models.base.LoginAttempt;
import com.project.questaidbackend.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginAttempt loginAttempt = new ObjectMapper().readValue(request.getInputStream(), LoginAttempt.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginAttempt.getEmail(), loginAttempt.getPassword() + "//" + loginAttempt.getType()
            );

            return authManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
        responseJson.put("authName", authResult.getName());
        responseJson.put("status", HttpServletResponse.SC_OK);

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseJson));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        GeneralResponseData generalResponseData = new GeneralResponseData(failed.getMessage(), "Unauthorized");
        response.getWriter().write(new ObjectMapper().writeValueAsString(generalResponseData));
        response.getWriter().flush();
    }
}
