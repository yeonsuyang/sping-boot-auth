package com.example.auth.config;

import com.example.auth.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer //인증 서버 설정 // 토큰 발행, 발행된 토큰 검증
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{

        //  clients.inMemory()
        //         .withClient("test_auth")
        //         .secret("{noop}secretId")
        //        .redirectUris("http://localhost:8080/oauth2/callback") //"http://localhost:4200**
        //         .authorizedGrantTypes("authorization_code")// implicit
        //        .scopes("read", "write") //토큰으로 접근할 수 있는 리소스 범위
        //         .accessTokenValiditySeconds(30000); // 발급 후 유효시간


        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //allow check token
                .allowFormAuthenticationForClients();
    }

    /*@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new JdbcTokenStore(dataSource)); // 인증이 성공하면 token 정보를 db에도 넣는다.
    }*/

    // https://jwt.io/

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints); //회원 정보 조회를 위해
        endpoints.accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailService);
    }

    private String signKey = "123@#$";

    @Bean //jwt converter
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signKey);
        return converter;
    }
}
