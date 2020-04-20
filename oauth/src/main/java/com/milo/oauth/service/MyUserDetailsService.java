package com.milo.oauth.service;

import com.milo.oauth.model.AuthenticatedUser;
import com.milo.oauth.model.BaseUser;
import com.milo.oauth.model.MyAuthority;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private AuthorityService authorityService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!"milo".equals(username)) {
      return null;
    }
    BaseUser baseUser = authorityService.getUser(username);
    if(baseUser == null){
      return null;
    }
    Set<MyAuthority> authoritiesSet = new HashSet<>(authorityService.getUserAuthoritiesList(username));
    AuthenticatedUser user = new AuthenticatedUser(username,baseUser.getAge(),baseUser.getGender(),baseUser.getPassword(),authoritiesSet);
    return user;
  }
}
