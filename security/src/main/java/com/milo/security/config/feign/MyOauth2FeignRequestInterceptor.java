package com.milo.security.config.feign;

import feign.RequestTemplate;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

public class MyOauth2FeignRequestInterceptor extends OAuth2FeignRequestInterceptor {

  private final OAuth2ClientContext oAuth2ClientContext;
  private final AccessTokenContextRelay accessTokenContextRelay;

  public MyOauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
      OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
    super(oAuth2ClientContext, resource);
    this.oAuth2ClientContext = oAuth2ClientContext;
    this.accessTokenContextRelay = accessTokenContextRelay;
  }

  /**
   * 根据header是否携带属性判断是否token中转
   * @param template
   */
  @Override
  public void apply(RequestTemplate template) {
//    Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
//    if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
//      return;
//    }

    accessTokenContextRelay.copyToken();
    if (oAuth2ClientContext != null && oAuth2ClientContext.getAccessToken() != null) {
      super.apply(template);
    }
  }
}
