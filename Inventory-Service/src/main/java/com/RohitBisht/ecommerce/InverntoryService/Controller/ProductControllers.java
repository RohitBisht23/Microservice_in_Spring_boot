package com.RohitBisht.ecommerce.InverntoryService.Controller;


import com.RohitBisht.ecommerce.InverntoryService.Clients.OrderFeignClient;
import com.RohitBisht.ecommerce.InverntoryService.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.InverntoryService.DTO.ProductDTO;
import com.RohitBisht.ecommerce.InverntoryService.Services.ProductServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductControllers {

    private final ProductServices productServices;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrderFeignClient orderFeignClient; //To make microservices to communicate with each others

    @GetMapping("/fetchOrderMessage")
    public String fetchOrderMessage() {
//        ServiceInstance orderService = discoveryClient.getInstances("Order-Service").getFirst();
//        return restClient
//                .get()
//                .uri(orderService.getUri()+"/orders/core/helloOrders")
//                .retrieve()
//                .body(String.class);
        return orderFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllInventories() {
        log.info("Trying to get all product inventories");
        return ResponseEntity.ok(productServices.getAllInventory());
    }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<ProductDTO> getInventoryById(@PathVariable Long productId) {
        log.info("Trying to fetch product from product inventory");
        return ResponseEntity.ok(productServices.getProductById(productId));
    }

    @PutMapping("/reduceStock")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDTO orders) {
        return ResponseEntity.ok(productServices.placeOrderAndReturnTotalPrice(orders));
    }

    @PutMapping("/addStocks")
    public ResponseEntity<String> addStock(@RequestBody OrderRequestDTO orders) {
        return ResponseEntity.ok(productServices.addStocks(orders));
    }
}