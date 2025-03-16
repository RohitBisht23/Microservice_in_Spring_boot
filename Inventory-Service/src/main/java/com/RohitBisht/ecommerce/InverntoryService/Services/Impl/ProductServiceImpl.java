package com.RohitBisht.ecommerce.InverntoryService.Services.Impl;

import com.RohitBisht.ecommerce.InverntoryService.DTO.OrderRequestDTO;
import com.RohitBisht.ecommerce.InverntoryService.DTO.OrderRequestItemDTO;
import com.RohitBisht.ecommerce.InverntoryService.DTO.ProductDTO;
import com.RohitBisht.ecommerce.InverntoryService.Entity.Product;
import com.RohitBisht.ecommerce.InverntoryService.Repository.ProductRepository;
import com.RohitBisht.ecommerce.InverntoryService.Services.ProductServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Double placeOrderAndReturnTotalPrice(OrderRequestDTO Orders) {
        log.info("Reducing the stocks");

        double totalPrice = 0.0;

        for(OrderRequestItemDTO orders : Orders.getItems()) {
            log.info("Getting product id and product quantity from the list of orders one by one");
            Long productId = orders.getProductId();
            Integer quantity = orders.getQuantity();

            log.info("Check if stocks are present in inventory with this give product id :{}", productId);

            Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("No product found with give product id :"+productId));
            if(product.getStock() < quantity) {
                log.info("Product quantity : {} and stock :{}", quantity, product.getStock());
                log.info("Quantity is very high than our inventory, we cannot fulfill the quantity.");
                throw new RuntimeException("Order quantity is higher than our inventory, Sorry we cannot fulfill the quantity.");
            }

            log.info("Reduce the quantity");
            product.setStock(product.getStock()-quantity);
            productRepository.save(product);

            log.info("Fetching One product price");
            Double oneProductPrice = product.getPrice();

            log.info("Calculating total price");
            totalPrice = totalPrice + (oneProductPrice * quantity);

            log.info("Reducing the stocks as well");
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public String addStocks(OrderRequestDTO orders) {
        log.info("Adding stocks");
        for(OrderRequestItemDTO items : orders.getItems()) {
            log.info("Fetch product id from the order items");
            Long productId = items.getProductId();

            log.info("Check if the product with given product id exist or not :{}",productId);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with given id :{}"+productId));

            Integer quantity = items.getQuantity();

            log.info("Add the quantity to the product id");
            Integer finalQuantity = product.getStock() + quantity;

            log.info("Save the final quantity to the inventory");
            product.setStock(finalQuantity);
            productRepository.save(product);
        }
        return "Product is successfully added to the inventory. Thank you";
    }
}