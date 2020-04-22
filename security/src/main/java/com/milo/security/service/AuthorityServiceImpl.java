package com.milo.security.service;

import com.milo.security.model.AuthenticatedUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;


@Component("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  public boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      AuthenticatedUser user = (AuthenticatedUser) principal;
      return user.getAuthorities().stream().filter(auth -> {
        return antPathMatcher.match(auth.getAuthority(), httpServletRequest.getRequestURI());
      }).findFirst().isPresent();
    }
    return false;
  }
}
