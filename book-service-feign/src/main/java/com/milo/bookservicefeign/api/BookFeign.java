package com.milo.bookservicefeign.api;

import com.milo.bookservicefeign.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "book-service")
public interface BookFeign {

  @GetMapping("/book/{id}")
  Book selectBookById(@PathVariable("id") Long id);
}