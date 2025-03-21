package com.RohitBisht.ecommerce.Order.Service.Services;

import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    OrderRequestDTO getProductById(Long orderId);

    List<OrderRequestDTO> getAllOrder();

    OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO);

    String cancelOrder(Long orderId);
}
