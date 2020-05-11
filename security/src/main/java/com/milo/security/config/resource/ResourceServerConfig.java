package com.milo.security.config.resource;

import com.milo.security.annotation.Oauth2EnableResourceServer;
import feign.RequestInterceptor;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@Oauth2EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  protected RemoteTokenServices remoteTokenServices;

  @Autowired
  protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

  @Autowired
  private OAuth2WebSecurityExpressionHandler expressionHandler;

  @Autowired
  private IgnoreUrlProperties ignoreUrlProperties;

  @Bean
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
    RestTemplate restTemplate = new RestTemplate();
    //保留原实现细节
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
          super.handleError(response);
        }
      }
    });
    return restTemplate;
  }


  /**
   * 资源的配置
   * 权限判断
   *
   * @param httpSecurity
   */
  @Override
  @SneakyThrows
  public void configure(HttpSecurity httpSecurity) {
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>
        .ExpressionInterceptUrlRegistry registry = httpSecurity
        .authorizeRequests();
    ignoreUrlProperties.getIgnoreUrls() // ignore url 不鉴权
        .forEach(url -> registry.antMatchers(url).permitAll());
    registry.anyRequest().access("@authorityService.hasPermission(request,authentication)")
        .and().csrf().disable();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    UserAuthenticationConverter userTokenConverter = new UserDetailsAuthenticationConverter();
    accessTokenConverter.setUserTokenConverter(userTokenConverter);
    remoteTokenServices.setRestTemplate(lbRestTemplate());
    remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
    resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
        .tokenServices(remoteTokenServices);
    resources.expressionHandler(expressionHandler); //替换表达式处理器
  }


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

//  /**
//   * 注入OAuth2FeignRequestInterceptor  有点蠢
//   */
//  @Bean
//  @ConditionalOnProperty("security.oauth2.client.client-id")
//  public RequestInterceptor OAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
//      OAuth2ProtectedResourceDetails resource) {
//    return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource, "Bearer", "Authorization");
//  }

}
