package com.RohitBisht.ecommerce.InverntoryService.Clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Order-Service", path="orders")
public interface OrderFeignClient {

    @GetMapping("/core/helloOrders")
    public String helloOrders();
}
