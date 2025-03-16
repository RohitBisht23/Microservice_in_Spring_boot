package com.RohitBisht.ecommerce.Order.Service.Clients;
import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Inventory-Service", path="/inventory")
public interface InventoryFeignClient {
    @PutMapping("/products/reduceStock")
    Double reduceStocks(@RequestBody OrderRequestDTO orderRequestDTO);

    @PutMapping("/products/addStocks")
    String addStocks(@RequestBody OrderRequestDTO orders);
}