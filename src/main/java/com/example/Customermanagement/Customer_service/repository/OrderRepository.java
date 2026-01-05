package com.example.Customermanagement.Customer_service.repository;

import com.example.Customermanagement.Customer_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
