package com.RohitBisht.ecommerce.Order.Service.Repository;


import com.RohitBisht.ecommerce.Order.Service.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
