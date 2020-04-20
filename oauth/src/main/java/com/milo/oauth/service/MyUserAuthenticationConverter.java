package com.milo.oauth.service;

import com.milo.oauth.model.AuthenticatedUser;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

public class MyUserAuthenticationConverter implements UserAuthenticationConverter {

  private static final String N_A = "N/A";
  /**
   * server check token - 添加额外user info
   * @param userAuthentication
   * @return
   */
  @Override
  public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put(USERNAME, userAuthentication.getName());
    if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty()) {
      response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));
    }
    AuthenticatedUser user = (AuthenticatedUser) userAuthentication.getPrincipal();
    response.put("user",user);
    response.put("xxxx","xxxxx");
    response.put("age", user.getAge());
    response.put("gender",user.getGender());
    return response;
  }

  /**
   *
   * @param map
   * @return
   */
  @Override
  public Authentication extractAuthentication(Map<String, ?> map) {
    if (map.containsKey(USERNAME)) {
      Object principal = map.get(USERNAME);
      Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
      AuthenticatedUser authenticatedUser = new AuthenticatedUser(principal.toString(), "", authorities);
      authenticatedUser.setUserName("Authenticated_User:" + principal.toString());
      authenticatedUser.setAge((String) map.get("age"));
      authenticatedUser.setGender((String) map.get("gender"));
      return new UsernamePasswordAuthenticationToken(authenticatedUser, "N/A", authorities);
    }
    return null;
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
    Object authorities = map.get(AUTHORITIES);
    if (authorities instanceof String) {
      return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
    }
    if (authorities instanceof Collection) {
      return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
          .collectionToCommaDelimitedString((Collection<?>) authorities));
    }
    throw new IllegalArgumentException("Authorities must be either a String or a Collection");
  }

}
