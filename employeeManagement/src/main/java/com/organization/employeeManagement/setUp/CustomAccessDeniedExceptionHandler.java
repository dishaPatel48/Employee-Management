package com.organization.employeeManagement.setUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    }
}
