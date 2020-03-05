package com.milo.order.controller.errorController;

import com.milo.order.model.Book;
import com.milo.order.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandleController {

  /**
   * 1. 实现ErrorController 测试转发
   * @return
   */
  @RequestMapping("/404/1")
  public String action4041(){
    return "404页面/1";
  }

  /**
   * 2. 实现ErrorPageRegistrar 测试转发
   * @return
   */
  @RequestMapping("/404/2")
  public String action4042(){
    return "404页面/2";
  }

  /**
   * 3. WebServerFactoryCustomizer 测试转发
   * @return
   */
  @RequestMapping("/404/3")
  public String action4043(){
    return "404页面/3";
  }

}
