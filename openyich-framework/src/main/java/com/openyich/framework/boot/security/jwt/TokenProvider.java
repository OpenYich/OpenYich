package com.openyich.framework.boot.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties.Security.Authentication.Jwt;
import com.openyich.framework.boot.utils.OpenYichAssert;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {

  private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
  private static final String AUTHORITIES_KEY = "roles";

  private String secret;
  private long tokenValidityInMilliseconds;
  private long tokenValidityInMillisecondsForRememberMe;

  public TokenProvider(OpenYichProperties openYichProperties) {
    Jwt jwt = openYichProperties.getSecurity().getAuthentication().getJwt();

    try {
      this.secret = OpenYichAssert.notNullWithFailover(() -> {
        log.debug("Using a Base64-encoded JWT secret key");
        return jwt.getBase64Secret();
      }, () -> {
        log.warn("Warning: the JWT secret key used is not Base64-encoded. "
            + "We recommend using the `openyich.security.authentication.jwt.base64-secret` key for optimum security.");
        return jwt.getSecret();
      });
    } catch (Exception e) {
      log.error("the JWT secret key must not be null.", e);
    }

    this.tokenValidityInMilliseconds = 1000 * jwt.getTokenValidityInSeconds();
    this.tokenValidityInMillisecondsForRememberMe =
        1000 * jwt.getTokenValidityInSecondsForRememberMe();
  }

  public String createToken(Authentication authentication, boolean rememberMe) {
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity;
    if (rememberMe) {
      validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
    } else {
      validity = new Date(now + this.tokenValidityInMilliseconds);
    }

    return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
        .signWith(SignatureAlgorithm.HS512, secret).setExpiration(validity).compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException e) {
      log.info("Invalid JWT signature.");
      log.trace("Invalid JWT signature trace: {}", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT token.");
      log.trace("Expired JWT token trace: {}", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT token.");
      log.trace("Unsupported JWT token trace: {}", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT token compact of handler are invalid.");
      log.trace("JWT token compact of handler are invalid trace: {}", e);
    } catch (Exception e) {
      log.info("Invalid JWT token.");
      log.trace("Invalid JWT token trace: {}", e);
    }
    return false;
  }

}
