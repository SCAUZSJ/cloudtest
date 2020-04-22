package com.milo.security.utils;

import com.milo.security.model.AuthenticatedUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 声明工具类
 */
@UtilityClass
public class AuthUtils {

  public Authentication getAnthentication(){
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public AuthenticatedUser getUser(){
    Authentication authentication = getAnthentication();
    if(authentication == null){
      return null;
    }
    Object principal = authentication.getPrincipal();
    return principal instanceof AuthenticatedUser? (AuthenticatedUser) principal : null;
  }
}
