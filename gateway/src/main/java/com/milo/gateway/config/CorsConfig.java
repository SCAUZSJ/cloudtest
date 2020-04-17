package com.milo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;
//
//@Configuration
//public class CorsConfig {
//
////  /**
////   * 跨域过滤器
////   *
////   * @return
////   */
////  private CorsConfiguration buildConfig() {
////    CorsConfiguration corsConfiguration = new CorsConfiguration();
////    corsConfiguration.addAllowedOrigin("*");
////    corsConfiguration.addAllowedHeader("*");
////    corsConfiguration.addAllowedMethod("*");
////    return corsConfiguration;
////  }
////
////
////  /**
////   * 跨域过滤器
////   *
////   * @return
////   */
////  @Bean
////  public CorsWebFilter corsFilter() {
////		/*UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		source.registerCorsConfiguration("/**", buildConfig());*/
////    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
////    source.registerCorsConfiguration("/**", buildConfig());
////    return new CorsWebFilter(source);
////  }
//
//}