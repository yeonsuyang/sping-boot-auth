
/*
package com.example.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
@EnableResourceServer //: 자원서버 설정, api 검증
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  /*
  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private HttpSessionRequestCache sessionRequestCache;

  @Autowired
  private SecurityContextRepository securityContextRepository;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

      resources
              .stateless(true)
              .tokenExtractor(new BearerTokenExtractor());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
      http
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authenticationProvider(authenticationProvider)
              .authorizeRequests()
              .antMatchers("/actuator/**", "/v2/api-docs", "/configuration/**", "/swagger**", "/webjars/**").permitAll()
              .antMatchers("/mgmt/v1/**", "/oauth/revoke").authenticated()
               // 인증된 사용자만 접근 가능.
              .antMatchers("/oauth/check").permitAll()
              // 전근을
              .anyRequest().authenticated()
              .and()
              .httpBasic()
//                .and()
//                .formLogin().permitAll()
              .and()
              .cors()
              .and()
              .csrf().disable();

      http.setSharedObject(SecurityContextRepository.class, securityContextRepository);
      http.setSharedObject(RequestCache.class, sessionRequestCache);


  }
} */