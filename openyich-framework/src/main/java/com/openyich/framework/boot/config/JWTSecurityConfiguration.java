package com.openyich.framework.boot.config;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;
import com.openyich.framework.boot.security.AuthoritiesConstants;
import com.openyich.framework.boot.security.jwt.JWTConfigurer;
import com.openyich.framework.boot.security.jwt.TokenProvider;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(OpenYichProperties.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import({SecurityProblemSupport.class, TokenProvider.class})
public class JWTSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final TokenProvider tokenProvider;
  private final CorsFilter corsFilter;
  private final SecurityProblemSupport problemSupport;
  private final List<String> ignorings;

  public JWTSecurityConfiguration(TokenProvider tokenProvider, CorsFilter corsFilter,
      SecurityProblemSupport problemSupport, OpenYichProperties openYichProperties) {
    this.tokenProvider = tokenProvider;
    this.corsFilter = corsFilter;
    this.problemSupport = problemSupport;
    this.ignorings = openYichProperties.getSecurity().getAuthentication().getJwt().getIgnorings();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    IgnoredRequestConfigurer configurer = web.ignoring()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .antMatchers("/**/*.{js,html}")
      .antMatchers("/i18n/**")
      .antMatchers("/content/**")
      .antMatchers("/h2-console/**")
      .antMatchers("/swagger-ui/index.html")
      .antMatchers("/test/**");
    
    // Customizing ignored request.
    ignorings.forEach(s -> configurer.antMatchers(s));
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling()
        .authenticationEntryPoint(problemSupport)
        .accessDeniedHandler(problemSupport)
      .and()
        .headers()
        .frameOptions().disable()
      .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .authorizeRequests()
        .antMatchers("/api/register").permitAll()
        .antMatchers("/api/activate").permitAll()
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers("/api/account/reset-password/init").permitAll()
        .antMatchers("/api/account/reset-password/finish").permitAll()
        .antMatchers("/api/**").authenticated()
        .antMatchers("/management/health").permitAll()
        .antMatchers("/management/info").permitAll()
        .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
      .and()
        .apply(securityConfigurerAdapter());
  }

  private JWTConfigurer securityConfigurerAdapter() {
    return new JWTConfigurer(tokenProvider);
  }
  
}