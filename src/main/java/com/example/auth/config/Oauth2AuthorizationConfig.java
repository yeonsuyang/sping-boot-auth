package com.example.auth.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
        import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
        import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer //인증 서버 설정 // 토큰 발행, 발행된 토큰 검증
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient("test_auth")
                .secret("testSecret")
                .redirectUris("http://localhost:8080/oauth2/callback") //"http://localhost:4200**
                .authorizedGrantTypes("authorization_code") // implicit
                .scopes("read","write") // 토큰으로 접근할 수 있는 리소스 범위
                .accessTokenValiditySeconds(30000); // 발급 후 유효시간
    }
}
