package com.milo.order.service;

import com.milo.order.model.Order;

public interface OrderService {
    Order selectOrderById(Long id);
}
