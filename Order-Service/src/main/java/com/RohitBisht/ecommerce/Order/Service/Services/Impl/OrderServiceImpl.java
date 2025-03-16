package com.RohitBisht.ecommerce.Order.Service.Services.Impl;


import com.RohitBisht.ecommerce.Order.Service.Clients.InventoryFeignClient;
import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.Order.Service.Entity.Order;
import com.RohitBisht.ecommerce.Order.Service.Entity.OrderItems;
import com.RohitBisht.ecommerce.Order.Service.Entity.OrderStatus;
import com.RohitBisht.ecommerce.Order.Service.Repository.OrderRepository;
import com.RohitBisht.ecommerce.Order.Service.Services.OrderService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ModelMapper modelMapper;
    private final InventoryFeignClient inventoryFeignClient;

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

    @Override
    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallBack")
    @RateLimiter(name ="inventoryRateLimiter", fallbackMethod = "createOrderFallBack")
    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {
        log.info("Calculating order price");
        Double TotalPrice = inventoryFeignClient.reduceStocks(orderRequestDTO);

        log.info("Placing order into db");
        Order order = modelMapper.map(orderRequestDTO, Order.class);

        for(OrderItems orderItems : order.getItems()) {
            orderItems.setOrder(order);
        }

        order.setTotalPrice(TotalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        Order savedOrder = repository.save(order);

        return modelMapper.map(savedOrder, OrderRequestDTO.class);
    }

    @Override
    public String cancelOrder(Long orderId) {
        log.info("Checking if given order id exist or not with id : {}",orderId);
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with  does not exists"+orderId));

        log.info("Order exists with given order id, now cancelling order");
        OrderRequestDTO orderRequestDTO = modelMapper.map(order, OrderRequestDTO.class);
        String orderAddedSuccessfully = inventoryFeignClient.addStocks(orderRequestDTO);

        return "Order have been successfully added";
    }


    //FallBack method
    public OrderRequestDTO createOrderFallBack(OrderRequestDTO orderRequestDTO, Throwable throwable) {
        log.error("Fall-Back error : {}",throwable.getMessage());
        return new OrderRequestDTO();
    }
}