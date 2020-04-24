package com.milo.security.annotation;


//import com.milo.security.config.feign.Oauth2FeignConfiguration;
import com.milo.security.config.feign.Oauth2FeignConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 资源服务注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({Oauth2FeignConfiguration.class})
public @interface Oauth2EnableResourceServer {

  /**
   * false：上下文获取用户名
   * true： 上下文获取全部用户信息
   *
   * @return
   */
  boolean details() default false;

}
