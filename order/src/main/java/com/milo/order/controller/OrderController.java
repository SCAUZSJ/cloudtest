package com.milo.order.controller;

import com.milo.order.model.Order;
import com.milo.order.service.OrderService;
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

  @GetMapping("/order/{id}")
  public Order selectOrderById(@PathVariable Long id, Authentication authentication){
    System.out.println("authentiaction-->"+authentication.getName());
    return orderService.selectOrderById(id);
  }


}
