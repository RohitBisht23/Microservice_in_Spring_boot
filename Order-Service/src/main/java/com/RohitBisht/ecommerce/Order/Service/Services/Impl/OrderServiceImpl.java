package com.RohitBisht.ecommerce.Order.Service.Services.Impl;


import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.Order.Service.Entity.Order;
import com.RohitBisht.ecommerce.Order.Service.Repository.OrderRepository;
import com.RohitBisht.ecommerce.Order.Service.Services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public OrderRequestDTO getProductById(Long orderId) {
        log.info("Getting order by id {}", orderId);
        Order order = repository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id :"+orderId));
        log.info("Successfully get the order");
        return modelMapper.map(order, OrderRequestDTO.class);
    }

    @Override
    public List<OrderRequestDTO> getAllOrder() {
        log.info("Fetching all the orders");
        List<Order> orders = repository.findAll();
        log.info("fetched all the orders");
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDTO.class)).toList();
    }
}