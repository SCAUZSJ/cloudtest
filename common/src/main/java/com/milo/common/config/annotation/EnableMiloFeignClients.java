package com.milo.common.config.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableMiloFeignClients {

  String[] value() default {};

  /**
   * 指定默认的扫描范围
   * @return 扫描范围
   */
  String[] basePackages() default {"com.milo"};

  Class<?>[] basePackageClasses() default {};

  Class<?>[] defaultConfiguration() default {};

  Class<?>[] clients() default {};
}
