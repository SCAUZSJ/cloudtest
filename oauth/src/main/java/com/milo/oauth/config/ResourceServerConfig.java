package com.milo.oauth.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 资源服务配置
 *
 * @ EnableResourceServer 启用资源服务
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired TokenStore tokenStore;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .requestMatchers()
        .antMatchers("/oauth/user/**")
        .and()
        .authorizeRequests()
        .antMatchers("/oauth/user/**")
        .authenticated();
  }

//  @Override
//  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//    //无状态
//    resources.stateless(true);
//    //设置token存储
//    resources.tokenStore(tokenStore);
//  }


//  /**
//   * 定义OAuth2请求匹配器
//   */
//  private static class OAuth2RequestedMatcher implements RequestMatcher {
//
//    @Override
//    public boolean matches(HttpServletRequest request) {
//      String auth = request.getHeader("Authorization");
//      //判断来源请求是否包含oauth2授权信息,这里授权信息来源可能是头部的Authorization值以Bearer开头,或者是请求参数中包含access_token参数,满足其中一个则匹配成功
//      boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
//      boolean haveAccessToken = request.getParameter("access_token") != null;
//      return haveOauth2Token || haveAccessToken;
//    }
//  }

//  @Bean
//  public TokenStore tokenStore() {
//    RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//    tokenStore.setPrefix("MILO_OAUTH:");
//    tokenStore.setSerializationStrategy(new JdkSerializationStrategy());
//    return tokenStore;
//  }


}
