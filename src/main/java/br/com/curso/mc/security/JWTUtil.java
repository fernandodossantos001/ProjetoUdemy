package br.com.curso.mc.security;

import io.jsonwebtoken.Claims;
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

    public  boolean isValidToken(String token) {
            Claims claims = getClaims(token);
            if(claims!=null){
                String username = claims.getSubject();
                Date expiration = claims.getExpiration();
                if(username != null && expiration != null & new Date(System.currentTimeMillis()).before(expiration)) {
                    return true;
                }
            }
        return false;
    }

    //Claims é um tipo do JWT, armazena as reivindicações do token, por exemplo, usuário e tempo de expiração.
    private  Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims!= null ? claims.getSubject():null;
    }

    public String generatToken(String userName){
        return Jwts.builder()
               .setSubject(userName)
               .setExpiration(new Date(System.currentTimeMillis() + expiration))
               .signWith(SignatureAlgorithm.HS512,secret.getBytes())
               .compact();
    }
}
