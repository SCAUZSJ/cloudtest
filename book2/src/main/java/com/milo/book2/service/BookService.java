package com.milo.book2.service;

import com.milo.book2.model.Book;

public interface BookService {
    Book selectBookById(Long id);
}
