package com.milo.book.service;

import com.milo.book.model.Book;

public interface BookService {
    Book selectBookById(Long id);
}
