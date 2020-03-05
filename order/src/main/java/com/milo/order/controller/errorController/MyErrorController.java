package com.milo.order.controller.errorController;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：Error code 自动转发 方法一
 * 日期：2020-03-05
 */
//@Controller
//public class MyErrorController implements ErrorController {
//
//  @Autowired
//  HttpServletRequest request;
//
//  @Override
//  @RequestMapping("/error")
//  public String getErrorPath() {
//    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//    if(statusCode.equals(404)){
//      return "/404/1";
//    }
//   return "xxxx";
//  }
//}
