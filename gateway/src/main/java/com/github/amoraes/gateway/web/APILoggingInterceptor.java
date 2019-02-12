package com.github.amoraes.gateway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class APILoggingInterceptor
  extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger("http.logger");
    private static Logger classLogger = LoggerFactory.getLogger(APILoggingInterceptor.class);

    @Override
    public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) {
        try {
            String info = request.getMethod() + " " + request.getRequestURL().toString();
            if (request.getQueryString() != null) {
                info += "?" + request.getQueryString();
            }
            log.info("Request: " + info);
        } catch (Exception e) {
            classLogger.error("Error logging request", e);
        }
        return true;
    }
 
    @Override
    public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler, 
      Exception ex) {
        try {
            log.info("Response: " + response.getStatus());
        } catch (Exception e) {
            classLogger.error("Error logging response", e);
        }
    }
}