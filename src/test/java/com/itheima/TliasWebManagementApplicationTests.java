package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testJwt(){
        Map<String,Object> claims = new HashMap<> (  );
        claims.put ( "id",1 );
        claims.put ( "name","tom" );
        String jwt = Jwts.builder ()
                .signWith ( SignatureAlgorithm.HS256 ,"itheima")
                .setClaims ( claims )
                .setExpiration ( new Date (System.currentTimeMillis ()) )
                .compact ();
        System.out.println ( jwt );
    }
    @Test
    public void testjwt(){
        Claims claims = Jwts.parser ()
                .setSigningKey ( "itheima" )
                .parseClaimsJws ( "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY5ODI0MTc3OH0.XGPVyU9fate9TKJTkSSvlmHaWA7wST7ECSYcv2NzWpQ" )
                .getBody ();
        System.out.println ( claims );
    }
}
