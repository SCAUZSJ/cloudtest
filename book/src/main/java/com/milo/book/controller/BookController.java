package com.milo.book.controller;

import com.milo.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.milo.book.service.BookService;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping(value = "book/{id}")
  public Book selectBookById(@PathVariable("id") Long id){
    return bookService.selectBookById(id);
  }

}
