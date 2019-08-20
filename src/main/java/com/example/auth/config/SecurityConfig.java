package com.example.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/*

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  customAuthorizationProvider defined in file [/home/ysyang/IdeaProjects/springboot-auth/out/production/classes/com/example/auth/config/CustomAuthorizationProvider.class]
↑     ↓
|  securityConfig defined in file [/home/ysyang/IdeaProjects/springboot-auth/out/production/classes/com/example/auth/config/SecurityConfig.class]
└─────┘

이 문제에 대한 해결 법
> PasswordEncoder @Bean을 securityConfig 안에 선언해놨었는데 그러니 빈의 순환 참조 발생 WebMvcConfig로 옮겼음.

 */

@Configuration
@RequiredArgsConstructor
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthorizationProvider authorizationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authorizationProvider);

        // inMemory일 때
        /*
        PasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication()
                  .withUser("ys")
                  .password((encoder.encode("ys")))
                // .password("{noop}1234")
                  .roles("ADMIN"); */
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


    // 이게 기본이라는데 안됨
    /*
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

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
