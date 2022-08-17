package com.lorbarh.orderservice.service;

import com.lorbarh.orderservice.dto.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);
}
