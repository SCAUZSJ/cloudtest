package com.milo.oauth.service;

import com.milo.oauth.model.MyAuthority;
import com.milo.oauth.model.MyUser;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!"milo".equals(username)) {
      return null;
    }
    Set<MyAuthority> authorities = new HashSet<>();
    authorities.add(new MyAuthority("order"));
    MyUser user = new MyUser(username, "123456", authorities);
    return user;
  }
}
