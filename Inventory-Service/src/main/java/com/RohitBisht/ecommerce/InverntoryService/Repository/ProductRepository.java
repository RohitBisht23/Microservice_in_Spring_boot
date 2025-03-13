package com.RohitBisht.ecommerce.InverntoryService.Repository;

import com.RohitBisht.ecommerce.InverntoryService.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
