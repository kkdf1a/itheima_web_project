package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info ( "员工登录{}",emp );
        Emp e = empService.login(emp);
        if (e != null){

            Map<String, Object> claims = new HashMap<> (  );
            claims.put ( "id",e.getId () );
            claims.put ( "name",e.getName () );
            claims.put ( "username",e.getUsername () );
            claims.put ( "password",e.getPassword () );
            String jwt = JwtUtils.generateJwt ( claims );
            return Result.success (jwt);
        }
        return Result.error ( "用户名或者密码错误" );
    }
}
