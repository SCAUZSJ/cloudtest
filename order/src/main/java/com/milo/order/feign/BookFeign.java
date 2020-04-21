package com.milo.order.feign;

import com.milo.bookservicefeign.model.Book;
import com.milo.order.config.resource.feign.FeignOauth2RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "book-service",configuration = FeignOauth2RequestInterceptor.class)
public interface BookFeign {

  @GetMapping("/book/{id}")
  Book selectBookById(@PathVariable("id") Long id);
}