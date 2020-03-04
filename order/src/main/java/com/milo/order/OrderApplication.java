package com.milo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication {

  @Bean
  @LoadBalanced //启用负载均衡 默认方式 轮询
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }

}
