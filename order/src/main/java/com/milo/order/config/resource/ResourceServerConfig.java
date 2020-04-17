package com.milo.order.config.resource;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  protected RemoteTokenServices remoteTokenServices;

  @Autowired
  protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

  @Autowired
  private OAuth2WebSecurityExpressionHandler expressionHandler;

  @Bean
//  @Qualifier
  public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
    OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
    expressionHandler.setApplicationContext(applicationContext);
    return expressionHandler;
  }

  /**
   * 服务调用-使用负载均衡
   * @return
   */
  @Bean
  @Primary
  @LoadBalanced
  public RestTemplate lbRestTemplate() {
    return new RestTemplate();
  }


  /**
   * 默认的配置，对外暴露
   * 增加了访问权限判断
   *
   * @param httpSecurity
   */
  @Override
  @SneakyThrows
  public void configure(HttpSecurity httpSecurity) {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>
        .ExpressionInterceptUrlRegistry registry = httpSecurity
        .authorizeRequests();
    registry.antMatchers("/order/**").permitAll();
//    permitAllUrlProperties.getIgnoreUrls()
//        .forEach(url -> registry.antMatchers(url).permitAll());
//    registry.anyRequest().access("@rbacService.hasPermission(request,authentication)")
//        .and().csrf().disable();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    UserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
    accessTokenConverter.setUserTokenConverter(userTokenConverter);
    remoteTokenServices.setRestTemplate(lbRestTemplate());
    remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
    resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
        .tokenServices(remoteTokenServices);
    resources.expressionHandler(expressionHandler); //替换表达式处理器
  }


}
