package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        BCryptPasswordEncoder encoder = passwordEncoder();

        auth.inMemoryAuthentication()
                .withUser("ys")
                .password((encoder.encode("ys")))
               // .password("{noop}1234")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception{
        security
                .csrf().disable()
                .headers().frameOptions().disable() //h2 사용을 위한
                .and()
                .authorizeRequests().antMatchers("/oauth/**", "oauth/token", "/oauth2/**", "/h2-console/*").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

/*
 @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
                .and()
                .cors()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable();

        http.setSharedObject(SecurityContextRepository.class, securityContextRepository());
        http.setSharedObject(RequestCache.class, sessionRequestCache());
        // @formatter:on
    }
 */