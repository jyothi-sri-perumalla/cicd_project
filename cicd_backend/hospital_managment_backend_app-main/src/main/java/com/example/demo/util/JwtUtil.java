package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.expiration}")
  private int jwtExpirationMs;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String generateToken(String email, Long adminId) {
    return Jwts.builder()
        .setSubject(email)
        .claim("adminId", adminId)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  public String generateOfficerToken(String mobileNumber, Long officerId) {
    return Jwts.builder()
        .setSubject(mobileNumber)
        .claim("officerId", officerId)
        .claim("userType", "officer")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  public String getEmailFromToken(String token) {
    return getClaims(token).getSubject();
  }

  public Long getAdminIdFromToken(String token) {
    return getClaims(token).get("adminId", Long.class);
  }

  public Long getOfficerIdFromToken(String token) {
    return getClaims(token).get("officerId", Long.class);
  }

  public String getUserTypeFromToken(String token) {
    return getClaims(token).get("userType", String.class);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaims(token).getExpiration();
  }

  public boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public boolean validateToken(String token) {
    try {
      getClaims(token);
      return !isTokenExpired(token);
    } catch (MalformedJwtException e) {
      System.err.println("Invalid JWT token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      System.err.println("JWT token is expired: " + e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.err.println("JWT token is unsupported: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.err.println("JWT claims string is empty: " + e.getMessage());
    }
    return false;
  }

  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
