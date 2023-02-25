package com.samgyeobsal.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Objects.equals(request.getMethod(), "POST")){
            Map<String, Object> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);

            log.info("request url : {}", request.getRequestURL());
            log.info("request info : {}", map);
            return true;
        }
        log.info("request url = {}", request.getRequestURL());
        log.info("request info = {}", request.getQueryString());

        return true;
    }
}
