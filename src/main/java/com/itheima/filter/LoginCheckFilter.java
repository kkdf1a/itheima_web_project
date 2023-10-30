package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        String url = req.getRequestURL ().toString ();
        log.info ( "请求头:{}",url );
        if(url.contains ( "login" )){
            log.info ( "登录" );
            filterChain.doFilter ( servletRequest,servletResponse );
            return;
        }
        String jwt = req.getHeader ( "token" );
        if(!StringUtils.hasLength ( jwt )){
            log.info ( "请求头token为空" );
            Result error = Result.error ( "NOT_LOGIN" );
            String notLogin = JSONObject.toJSONString ( error );
            resp.getWriter ().write ( notLogin );
            return;
        }

        try {
            JwtUtils.parseJWT ( jwt );
        } catch (Exception e) {
            e.printStackTrace ();
            log.info ( "解析令牌失败" );
            Result error = Result.error ( "NOT_LOGIN" );
            String notLogin = JSONObject.toJSONString ( error );
            resp.getWriter ().write ( notLogin );
            return;
        }
        log.info ( "登录成功放行" );
        filterChain.doFilter ( servletRequest,servletResponse );
    }
}
