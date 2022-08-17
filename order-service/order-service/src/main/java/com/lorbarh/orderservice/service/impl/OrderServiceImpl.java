package com.lorbarh.orderservice.service.impl;

import com.lorbarh.orderservice.dto.OrderLineItemsDto;
import com.lorbarh.orderservice.dto.OrderRequest;
import com.lorbarh.orderservice.model.Order;
import com.lorbarh.orderservice.model.OrderLineItems;
import com.lorbarh.orderservice.repository.OrderRepository;
import com.lorbarh.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));

        List<OrderLineItems> orderLineItems = new ArrayList<>();
        for (OrderLineItemsDto orderLineItemsDto : orderRequest.getOrderLineItemsDtoList()) {
            OrderLineItems lineItems = mapToDto(orderLineItemsDto);
            orderLineItems.add(lineItems);
        }

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }

}
