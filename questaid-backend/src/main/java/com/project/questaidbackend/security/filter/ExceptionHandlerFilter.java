package com.project.questaidbackend.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.base.GeneralResponseData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            GeneralResponseData generalResponseData = new GeneralResponseData("JWT NOT VALID", response.getStatus());
            response.getWriter().write(new ObjectMapper().writeValueAsString(generalResponseData));
            response.getWriter().flush();
        } catch (EntityNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            GeneralResponseData generalResponseData = new GeneralResponseData("Email doesn't exist", response.getStatus());
            response.getWriter().write(new ObjectMapper().writeValueAsString(generalResponseData));
            response.getWriter().flush();
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            GeneralResponseData generalResponseData = new GeneralResponseData("Bad Request", response.getStatus());
            response.getWriter().write(new ObjectMapper().writeValueAsString(generalResponseData));
            response.getWriter().flush();
        }
    }
}
