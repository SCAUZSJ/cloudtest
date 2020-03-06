package com.milo.zuul.configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 前置过滤器
 */
@Component
public class MyPreFilter extends ZuulFilter {
//  @Value("${default.login.prefix}")
  private String defaultLoginPrefix;

//  @Value("${default.login.time}")
  private int loginTime;

//  @Autowired
//  private JedisCluster redisUtils;


  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * 解决跨域/token校验
   * 拦截器具体的代码
   * @return
   */
  @Override
  public Object run() {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();
    HttpServletResponse response = requestContext.getResponse();
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Requested-With");

    //TODO 检验token，在Header中获取Authorization，再从redis中获取用户信息，set到zuul的request中后台使用


    String accessToken = request.getHeader("Authorization");
    if(StringUtils.isBlank(accessToken)){
      return null;
    }
    String userInfo = getUserInfo(accessToken);

    try {
      requestContext.addZuulRequestHeader("userInfo", URLEncoder.encode(userInfo, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return null;
  }

  private String getUserInfo(String accessToken) {
    //TODO
    System.out.println("UserInfo = " + accessToken);
    return accessToken;
  }
}
