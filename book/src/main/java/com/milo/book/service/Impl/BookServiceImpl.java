package com.milo.book.service.Impl;

import com.milo.book.model.Book;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.milo.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  private static final Map<Long, Book> ITEM_MAP = new HashMap<Long, Book>();

  static {// 准备一些静态数据,模拟数据库
    ITEM_MAP.put(1L, new Book(1L, "商品1", 1000L));
    ITEM_MAP.put(2L, new Book(2L, "商品2", 2000L));
    ITEM_MAP.put(3L, new Book(3L, "商品3", 3000L));
    ITEM_MAP.put(4L, new Book(4L, "商品4", 4000L));
    ITEM_MAP.put(5L, new Book(5L, "商品5", 5000L));
    ITEM_MAP.put(6L, new Book(6L, "商品6", 6000L));
    ITEM_MAP.put(7L, new Book(7L, "商品7", 7000L));
    ITEM_MAP.put(8L, new Book(8L, "商品8", 8000L));
    ITEM_MAP.put(8L, new Book(9L, "商品9", 9000L));
    ITEM_MAP.put(8L, new Book(10L, "商品10", 10000L));
  }

  /**
   * 模拟实现商品查询
   *
   * @param id
   * @return
   */
  @Override
  public Book selectBookById(Long id) {
    return ITEM_MAP.get(id);
  }
}
