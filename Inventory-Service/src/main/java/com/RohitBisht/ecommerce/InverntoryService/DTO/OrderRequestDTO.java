package com.RohitBisht.ecommerce.InverntoryService.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private  List<OrderRequestItemDTO> items;
}
