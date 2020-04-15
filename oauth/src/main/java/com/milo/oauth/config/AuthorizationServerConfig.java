package com.milo.oauth.config;

import com.milo.oauth.service.MyClientDetailsService;
import com.milo.oauth.service.MyUserDetailsService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private MyClientDetailsService clientDetailsService;

  @Autowired
  private MyUserDetailsService userDetailsService;


  @Bean
  public TokenStore tokenStore() {
    RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
    tokenStore.setPrefix("MILO_OAUTH:");
    tokenStore.setSerializationStrategy(new JdkSerializationStrategy());
    return tokenStore;
  }

  @Bean
  public AuthorizationServerTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    //access_token 的超时时间
    defaultTokenServices.setAccessTokenValiditySeconds(60 * 30);
    //refresh_token 的超时时间
    defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 7);
    //支持access_token 刷新
    defaultTokenServices.setSupportRefreshToken(true);
    //使用refresh_token刷新之后该refresh_token是否依然使用，默认是依然使用
    defaultTokenServices.setReuseRefreshToken(true);
    //access_token的存储方式，这个在配置文件中配
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setTokenEnhancer(tokenEnhancer());

    return defaultTokenServices;
  }


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .authenticationManager(authenticationManager) //配置认证管理器
        .tokenServices(tokenServices()) //配置token存储的服务与位置
        .tokenStore(tokenStore())
        .userDetailsService(userDetailsService); //配置用户服务
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
  }


  /**
   * token增强
   */
  @Bean
  public TokenEnhancer tokenEnhancer() {
    return (accessToken, authentication) -> {
      final Map<String, Object> additionalInfo = new HashMap<>(1);
      additionalInfo.put("Enhancer_Params", "xxxxxx");
      ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
      return accessToken;
    };
  }
}
