package com.milo.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseUser {
  private String userName;
  private String age;
  private String gender;
  private String password;
}
