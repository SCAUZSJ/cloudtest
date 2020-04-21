package com.milo.order.config.resource.model;

import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class AuthenticatedUserInfo extends User {

  private String userName;
  private String age;
  private String gender;
  private List<String> urls;

  public AuthenticatedUserInfo(String userName, String password, Collection<? extends GrantedAuthority> authorities){
    super(userName,password,authorities);
  }

  public AuthenticatedUserInfo(String userName, String age, String gender, List<String> urls, String password, Collection<? extends GrantedAuthority> authorities) {
    super(userName, password, authorities);
    this.userName = userName;
    this.age = age;
    this.gender = gender;
    this.urls = urls;
  }


}
