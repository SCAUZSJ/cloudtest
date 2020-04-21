package com.milo.order.config.resource.feign;

import com.milo.order.config.resource.utils.AuthUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class FeignOauth2RequestInterceptor implements RequestInterceptor {


  public static final String BEARER = "bearer";
  public static final String AUTHORIZATION = "Authorization";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    Authentication authentication = AuthUtils.getAnthentication();
    if(authentication!=null && authentication.getDetails() instanceof OAuth2AuthenticationDetails){
      OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
      requestTemplate.header(AUTHORIZATION,String.format("%s %s", BEARER,details.getTokenValue()));
    }
   }
}
