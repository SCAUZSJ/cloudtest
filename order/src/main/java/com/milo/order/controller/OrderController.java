package com.milo.order.controller;

import com.milo.bookservicefeign.api.BookFeign;
import com.milo.order.model.Order;
import com.milo.order.service.OrderService;
import com.milo.security.model.AuthenticatedUser;
import com.milo.security.utils.AuthUtils;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Resource
  private BookFeign bookFeign;

  @GetMapping("/order/info/{id}")
  public Order selectOrderById(@PathVariable Long id, Authentication authentication){
    System.out.println("authentiaction_user_name_from_params-->"+authentication.getName());
    AuthenticatedUser user = AuthUtils.getUser();
    if(user!= null){
      System.out.println("authentiaction_user_name_from_utils -->" + user.getUserName());
    }
    return orderService.selectOrderById(id);
  }

  @GetMapping("/order/common")
  public String doSomething(){
    System.out.println("do something without Authentication");
    System.out.println(bookFeign.selectBookById(1L));
    return "done";
  }

  @GetMapping("/order/commonWithFromIn")
  public String doSomething2(){
    System.out.println("do something without Authentication");
    System.out.println(bookFeign.selectBookById(1L));
    return "done";
  }


}
