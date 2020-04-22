package com.milo.book;

import com.milo.common.config.annotation.EnableMiloFeignClients;
import com.milo.security.annotation.Oauth2EnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMiloFeignClients
@Oauth2EnableResourceServer
public class BookApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
  }

}
