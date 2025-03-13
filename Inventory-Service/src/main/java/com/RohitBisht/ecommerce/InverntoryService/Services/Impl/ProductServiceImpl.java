package com.RohitBisht.ecommerce.InverntoryService.Services.Impl;

import com.RohitBisht.ecommerce.InverntoryService.DTO.ProductDTO;
import com.RohitBisht.ecommerce.InverntoryService.Entity.Product;
import com.RohitBisht.ecommerce.InverntoryService.Repository.ProductRepository;
import com.RohitBisht.ecommerce.InverntoryService.Services.ProductServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductDTO> getAllInventory() {
        log.info("Fetching all the inventory from DB");
        List<ProductDTO> list = productRepository.findAll()
                .stream()
                .map(product ->
                    mapper.map(product, ProductDTO.class)
                ).toList();
        log.info("Fetched all inventory");
        return list;
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        log.info("Fetching inventory from DB with {} Id",productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with give id"));

        log.info("Product successfully found");
        return mapper.map(product, ProductDTO.class);
    }
}