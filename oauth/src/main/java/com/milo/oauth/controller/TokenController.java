package com.milo.oauth.controller;

import com.milo.oauth.model.AuthenticatedUser;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/user")
public class TokenController {

  @GetMapping("/getUserInfo")
  public AuthenticatedUser me(Authentication authentication) {
    AuthenticatedUser user = (AuthenticatedUser)authentication.getPrincipal();
    System.out.println("userName-->"+ user.getUserName());
    return user;
  }
}
