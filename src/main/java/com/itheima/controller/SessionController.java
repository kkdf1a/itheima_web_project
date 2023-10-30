package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController

public class SessionController {
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie ( new Cookie ( "loginname","itheima" ) );
        return Result.success ();
    }

    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies ();
        for (Cookie cookie:cookies){
          if(cookie.getName ().equals ( "loginname" )){
              System.out.println ( cookie.getValue () );
          }
        }
        //response.addCookie ( new Cookie ( "loginname","itheima" ) );
        return Result.success ();
    }

    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info ( "{}",session.hashCode () );
        session.setAttribute ( "loguser","tom" );
        return Result.success ();
    }

    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        HttpSession session = request.getSession ();
        log.info ( "{}",session.hashCode () );
        //session.setAttribute ( "loguser","tom" );
        Object user = session.getAttribute ( "loguser" );
        log.info ( "{}",user );
        return Result.success ();
    }
}
