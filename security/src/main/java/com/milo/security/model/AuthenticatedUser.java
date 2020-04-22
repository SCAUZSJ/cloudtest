package com.milo.security.model;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class AuthenticatedUser extends User {

  private String userName;
  private String age;
  private String gender;

  public AuthenticatedUser(String userName, String password, Collection<? extends GrantedAuthority> authorities){
    super(userName,password,authorities);
  }

  public AuthenticatedUser( String userName, String age, String gender, String password, Collection<? extends GrantedAuthority> authorities) {
    super(userName, password, authorities);
    this.userName = userName;
    this.age = age;
    this.gender = gender;
  }


}
