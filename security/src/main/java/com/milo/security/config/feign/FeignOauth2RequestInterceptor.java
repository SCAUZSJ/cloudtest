package com.milo.security.config.feign;

import com.milo.common.constant.SecurityConstants;
import com.milo.security.utils.AuthUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;


/**
 * Feign Oauth2 拦截器
 */
public class FeignOauth2RequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    System.out.println(requestTemplate.path());
    Authentication authentication = AuthUtils.getAnthentication();
    if(authentication!=null && authentication.getDetails() instanceof OAuth2AuthenticationDetails){
      OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
      requestTemplate.header(SecurityConstants.AUTHORIZATION,String.format("%s %s", SecurityConstants.BEARER,details.getTokenValue()));
    }
   }
}
