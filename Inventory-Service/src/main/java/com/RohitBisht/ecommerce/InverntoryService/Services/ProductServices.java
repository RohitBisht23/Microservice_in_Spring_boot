package com.RohitBisht.ecommerce.InverntoryService.Services;


import com.RohitBisht.ecommerce.InverntoryService.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.InverntoryService.DTO.ProductDTO;

import java.util.List;

public interface ProductServices {

    public List<ProductDTO> getAllInventory();

    public ProductDTO getProductById(Long productId);

    Double placeOrderAndReturnTotalPrice(OrderRequestDTO orders);

    String addStocks(OrderRequestDTO orders);
}
