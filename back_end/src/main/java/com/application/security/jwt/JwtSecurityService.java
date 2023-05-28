package com.application.security.jwt;

import com.application.dto.UserAuthenticationDTO;

import com.application.dto.UserDTO;
import com.application.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtSecurityService {

    @Value("${jwt.secret_key}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expirationInSeconds;
    @Value("${jwt.issuer}")
    private String issuer;

    public TokenDetails generateToken(UserAuthenticationDTO userDTO){
        return generateToken(new HashMap<>(), userDTO);
    }

    public TokenDetails generateToken(Map<String, Object> claims, UserAuthenticationDTO userDTO){
        Long expirationInMilliseconds = expirationInSeconds * 1000L;
        Date expirationDate = new Date(new Date().getTime() + expirationInMilliseconds);

        return generateToken(expirationDate, claims, userDTO);
    }

    public TokenDetails generateToken(Date expirationDate,
                                      Map<String, Object> claims,
                                      UserAuthenticationDTO userDTO){
        Date date = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDTO.getLogin())
                .setIssuer(issuer)
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .setId(UUID.randomUUID().toString())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        return TokenDetails.builder()
                .token(token)
                .issuedAt(date)
                .expiresAt(expirationDate).build();
    }


    public String extractLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String login = extractLogin(token);
        return (login.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
