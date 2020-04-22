package com.milo.oauth.service;

import com.milo.oauth.model.BaseUser;
import com.milo.oauth.model.MyAuthority;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.Serializers.Base;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

  final static List<String> DB_USER_LIST = Arrays.asList("milo","kkk");
  final static Map<String, BaseUser> DB_BASE_USER_INFO = new HashMap<String, BaseUser>(){{
    put("milo", new BaseUser("milo","20","male","123456"));
    put("kkk", new BaseUser("kkk","19","female","123456"));
  }};
  final static Map<String, List<MyAuthority>> DB_AUTHORITIES = new HashMap<String, List<MyAuthority>>(){{
    put("milo",Arrays.asList(new MyAuthority("/order/info/**"),new MyAuthority("/book/**")));
    put("kkk",null);
  }};

  /**
   * 模拟查询DB - user
   * @param userName
   * @return isExist user
   */
  public boolean hasUser(String userName){
    return  this.DB_USER_LIST.contains(userName);
  }

  /**
   * 模拟查询DB - user base info
   * @param userName
   * @return Object BaseInfo
   */
  public BaseUser getUser(String userName){
    if(!hasUser(userName)){
      return null;
    }
    return DB_BASE_USER_INFO.get(userName);
  }

  /**
   * 模拟查询DB - user authorities
   * @param userName
   * @return Object List
   */
  public List<MyAuthority> getUserAuthoritiesList(String userName){
    return DB_AUTHORITIES.get(userName);
  }

}
