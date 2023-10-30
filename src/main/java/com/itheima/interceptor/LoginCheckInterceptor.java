package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println ( "prehandle..." );
        String jwt = request.getHeader ( "token" );
        log.info ( "{}",jwt );
        if(!StringUtils.hasLength ( jwt )){
            log.info ( "请求头token为空" );
            Result error = Result.error ( "NOT_LOGIN" );
            String notLogin = JSONObject.toJSONString ( error );
            response.getWriter ().write ( notLogin );
            return false;
        }

        try {
            JwtUtils.parseJWT ( jwt );
        } catch (Exception e) {
            e.printStackTrace ();
            log.info ( "解析令牌失败" );
            Result error = Result.error ( "NOT_LOGIN" );
            String notLogin = JSONObject.toJSONString ( error );
            response.getWriter ().write ( notLogin );
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println ( "posthandle.." );
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println ( "after.." );
    }
}
