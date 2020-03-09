package com.example.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
class ResourceServerSecurityConfig extends ResourceServerConfigurerAdapter{

    TokenStore jwtTokenStore;
    //oauth2-resource
    //
    public void configure(HttpSecurity http) throws Exception {
        //token endpint 로 토큰을 받고 나머지는 권한이 있어야 한다.
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/oauth2/**", "/h2-console/*",
                        "/authorization", "/token/**", "/confirmCode", "/users/**").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void  configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(jwtTokenStore);
    }
}

