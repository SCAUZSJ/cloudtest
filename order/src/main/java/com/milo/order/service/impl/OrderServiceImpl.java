package com.milo.order.service.impl;

import com.milo.order.model.Book;
import com.milo.order.model.Order;
import com.milo.order.service.OrderService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

  //提供的用于访问Rest服务的客户端
  @Autowired
  private RestTemplate restTemplate;

  private static final Map<Long, Order> ITEM_MAP = new HashMap<Long, Order>();

  static {// 准备一些静态数据,模拟数据库
    ITEM_MAP.put(101L, new Order(101L, Arrays.asList(1L,2L), LocalDateTime.now()));
    ITEM_MAP.put(102L, new Order(102L, Arrays.asList(2L,2L,3L), LocalDateTime.now()));
    ITEM_MAP.put(103L, new Order(103L, Arrays.asList(3L,4L), LocalDateTime.now()));
  }

  /**
   * 模拟实现商品查询
   *
   * @param id
   * @return
   */
  @Override
  public Order selectOrderById(Long id) {
    String bookServiceUrl = "http://book-service/book/";

    Order order = ITEM_MAP.get(id);
    for(Long bookId : order.getBookIds()){
      System.out.println(restTemplate.getForObject(bookServiceUrl+bookId, Book.class));
    }
    return ITEM_MAP.get(id);
  }
}
