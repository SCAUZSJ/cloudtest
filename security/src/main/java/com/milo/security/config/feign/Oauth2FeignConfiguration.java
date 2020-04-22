package com.milo.security.config.feign;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
@ConditionalOnClass(Feign.class)
@ComponentScan("com.milo.security")
public class Oauth2FeignConfiguration {

  @Bean
  @ConditionalOnProperty("security.oauth2.client.client-id")
  public RequestInterceptor OAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
      OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
    return new MyOauth2FeignRequestInterceptor(oAuth2ClientContext, resource, accessTokenContextRelay);
  }
}
