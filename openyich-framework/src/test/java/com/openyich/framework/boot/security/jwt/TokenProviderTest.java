package com.openyich.framework.boot.security.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Lists;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties;
import com.openyich.framework.boot.security.AuthoritiesConstants;

public class TokenProviderTest {

  private static final Logger log = LoggerFactory.getLogger(TokenProviderTest.class);

  private TokenProvider tokenProvider;
  private OpenYichProperties openYichProperties;
  private UsernamePasswordAuthenticationToken authentication;

  @Before
  public void setup() {
    this.openYichProperties = new OpenYichProperties();

    List<GrantedAuthority> authorities = Lists.newArrayList();
    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));

    this.authentication = new UsernamePasswordAuthenticationToken("testuser", "", authorities);
  }

  @Test
  public void testWithSecret() {
    String secret =
        "df3cefe019b698422815c55cdd99578bae8d00cbd491ff49b56d43d8f3a901c62f54856bf0dfd62a0320b6e97816663d282e003c82148e339ae4c22043dfc87a";
    openYichProperties.getSecurity().getAuthentication().getJwt().setSecret(secret);
    this.tokenProvider = new TokenProvider(openYichProperties);

    String token = tokenProvider.createToken(authentication, false);
    log.info("Create Token: {}", token);
    assertThat(token).isNotBlank();

    boolean val = tokenProvider.validateToken(token);
    assertThat(val).isEqualTo(true);
    val = !val;
    assertThat(val).isEqualTo(false);

    Authentication authentication = tokenProvider.getAuthentication(token);
    assertThat(authentication.getName()).isEqualTo("testuser");
  }

  @Test
  public void testWithBase64Secret() {
    String base64Secret =
        "ZGYzY2VmZTAxOWI2OTg0MjI4MTVjNTVjZGQ5OTU3OGJhZThkMDBjYmQ0OTFmZjQ5YjU2ZDQzZDhmM2E5MDFjNjJmNTQ4NTZiZjBkZmQ2MmEwMzIwYjZlOTc4MTY2NjNkMjgyZTAwM2M4MjE0OGUzMzlhZTRjMjIwNDNkZmM4N2E=";
    openYichProperties.getSecurity().getAuthentication().getJwt().setBase64Secret(base64Secret);
    this.tokenProvider = new TokenProvider(openYichProperties);

    String token = tokenProvider.createToken(authentication, false);
    log.info("Create Token: {}", token);
    assertThat(token).isNotBlank();

    boolean val = tokenProvider.validateToken(token);
    assertThat(val).isEqualTo(true);
    val = !val;
    assertThat(val).isEqualTo(false);

    Authentication authentication = tokenProvider.getAuthentication(token);
    assertThat(authentication.getName()).isEqualTo("testuser");
  }

}
