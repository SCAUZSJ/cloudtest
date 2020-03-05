package com.milo.order.configuration.errorPage;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 描述：Error code 自动转发 方法二
 * 日期：2020-03-05
 */
@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {

  @Override
  public void registerErrorPages(ErrorPageRegistry registry) {
    ErrorPage[] errorPages = new ErrorPage[1];
    errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404/2");
    registry.addErrorPages(errorPages);
  }
}

