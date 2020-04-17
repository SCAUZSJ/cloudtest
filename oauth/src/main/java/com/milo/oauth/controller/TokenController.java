package com.milo.oauth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/user")
public class TokenController {

  @GetMapping("/getUserInfo")
  public Principal me(Principal me) {
    System.out.println("userName-->"+me.getName());
//    Principal p = new Principal() {
//      @Override
//      public String getName() {
//        return "mmm";
//      }
//    };
    return me;
  }
}
