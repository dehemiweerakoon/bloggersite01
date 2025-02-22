package com.example.bloggersite01.Security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${ijseapp.jwt.secret}")
    private  String jwtSecret;

    @Value("${ijseapp.jwt.jwtExpiration}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }// this line will generate the Token in this context
    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token:{}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT Token:{}",e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token:{}",e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("JET payload is empty :{}",e.getMessage());
        }
        return false;
    }
    public String getUsernameFormJwtToken(String authToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
    }
}
