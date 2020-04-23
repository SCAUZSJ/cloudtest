package com.milo.bookservicefeign.api;

import com.milo.bookservicefeign.model.Book;
import com.milo.common.constant.SecurityConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


//configuration = FeignOauth2RequestInterceptor.class
@FeignClient(value = "book-service")
public interface BookFeign {

  @GetMapping("/book/{id}")
  Book selectBookById(@PathVariable("id") Long id);
}