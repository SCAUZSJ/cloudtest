package com.milo.security.config.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * 异常处理 - 细化
 * AuthenticationException
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

  @Override
  @SneakyThrows
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) {

    System.out.println("Resource Exception: --->"+e.getMessage());
  }
}
