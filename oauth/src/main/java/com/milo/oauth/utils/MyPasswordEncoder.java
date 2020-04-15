package com.milo.oauth.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return String.valueOf(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    System.out.println("tttt"+encodedPassword);
    return encodedPassword.contentEquals(rawPassword);
  }
}
