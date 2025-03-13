package com.RohitBisht.ecommerce.InverntoryService.DTO;

import lombok.Data;


@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private Integer stock;
}
