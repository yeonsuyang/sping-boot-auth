package com.example.auth.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
        import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
        import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient("test_auth")
                .secret("testSecret")
                .redirectUris("http://localhost:8080/oauth2/callback")
                .authorizedGrantTypes("authorization_code")
                .scopes("read","write")
                .accessTokenValiditySeconds(30000);
    }
}
