package com.milo.gateway.config.limit;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfig {

  public static final String URI = "uri";

  @Bean
  public KeyResolver MyKeyResolver() {
    return exchange -> Mono.just(getKey(exchange));
  }

  private String getKey(ServerWebExchange exchange) {
    Map<String, String> keys = new HashMap<>();
    keys.put(URI, exchange.getRequest().getPath().toString());
    // todo
    return JSON.toJSONString(keys);

  }
}
