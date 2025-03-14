package com.RohitBisht.ecommerce.Order.Service.Controller;

import com.RohitBisht.ecommerce.Order.Service.Clients.InventoryFeignClient;
import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.Order.Service.DTO.OrderRequestItemDTO;
import com.RohitBisht.ecommerce.Order.Service.Services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/core")
public class OrderController {


    private final OrderService orderService;
    private final InventoryFeignClient inventoryFeignClient;

    @GetMapping
    public ResponseEntity<List<OrderRequestDTO>> getAllInventories() {
        log.info("Trying to get all product inventories");
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/getById/{orderId}")
    public ResponseEntity<OrderRequestDTO> getInventoryById(@PathVariable Long orderId) {
        log.info("Trying to fetch product from product inventory");
        return ResponseEntity.ok(orderService.getProductById(orderId));
    }

    @GetMapping("/helloOrders")
    public String helloOrder() {
        return "Hello from the order service";
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderRequestDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
       OrderRequestDTO orderRequestDTO1 = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderRequestDTO1);
    }
}
