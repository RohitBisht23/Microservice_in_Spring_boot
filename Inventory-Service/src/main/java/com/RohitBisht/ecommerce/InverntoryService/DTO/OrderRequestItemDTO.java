package com.RohitBisht.ecommerce.InverntoryService.DTO;

import lombok.Data;

@Data
public class OrderRequestItemDTO {
    private Long productId;
    private Integer quantity;
}
