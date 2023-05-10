package com.example.practiceallflow.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("△ Filter - init : 필터 서블릿 초기화");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("△ Filter - doFilter start : 필터 시작");
        chain.doFilter(request, response);
        log.info("△ Filter - doFilter end : 필터 끝");
    }

    @Override
    public void destroy() {
        log.info("△ Filter - destroy : 필터 서블릿 제거");
        Filter.super.destroy();
    }
}
