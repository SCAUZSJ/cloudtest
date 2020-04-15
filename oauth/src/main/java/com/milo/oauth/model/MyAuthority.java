package com.milo.oauth.model;

import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;

public class MyAuthority implements GrantedAuthority {

  private String authority;

  public MyAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyAuthority that = (MyAuthority) o;
    return Objects.equals(authority, that.authority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authority);
  }
}
