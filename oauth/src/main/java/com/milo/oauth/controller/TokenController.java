package com.milo.oauth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

  @GetMapping("/user/getUserInfo")
  public Principal me(Principal me) {
    System.out.println("User：" + me);
    System.out.println(me.getName());
//    Principal p = new Principal() {
//      @Override
//      public String getName() {
//        return "mmm";
//      }
//    };
    return me;
  }
}
