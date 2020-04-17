package com.milo.order.config.resource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * 客户端异常处理 1. 可以根据 AuthenticationException 不同细化异常处理
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

  @Override
  @SneakyThrows
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) {

    System.out.println("exception: --->"+e.getMessage());
  }
}
