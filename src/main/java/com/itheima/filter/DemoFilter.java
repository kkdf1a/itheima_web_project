package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println ( "初始化方法执行" );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println ( "demo拦截" );
        filterChain.doFilter ( servletRequest,servletResponse );
        System.out.println ( "demo拦截后" );
    }

    @Override
    public void destroy() {
        System.out.println ( "destroy方法执行" );
    }
}
