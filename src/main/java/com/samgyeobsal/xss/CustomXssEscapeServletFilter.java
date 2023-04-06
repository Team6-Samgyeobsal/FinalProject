package com.samgyeobsal.xss;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomXssEscapeServletFilter extends XssEscapeServletFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String contextPath = httpRequest.getContextPath();

        // 제외할 URL 패턴을 정규식으로 지정
        String excludePattern = "/web/mypage/maker/funding/[a-zA-Z0-9\\-]+/story";

        if (requestURI.matches(excludePattern)) {
            log.info("필터링 제외 url ");
            chain.doFilter(request, response);
            return;
        }

        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
