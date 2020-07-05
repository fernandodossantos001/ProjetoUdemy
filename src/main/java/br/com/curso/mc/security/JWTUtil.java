package br.com.curso.mc.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generatToken(String userName){
        return Jwts.builder()
               .setSubject(userName)
               .setExpiration(new Date(System.currentTimeMillis() + expiration))
               .signWith(SignatureAlgorithm.HS512,secret.getBytes())
               .compact();
    }
}