package com.milo.order.config.resource.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;


public interface AuthorityService {

  /**
   * 请求鉴权 - 根据user permission与请求url判断
   * @param httpServletRequest
   * @param authentication
   * @return
   */
  boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);
}
