package com.example.ordermanagement.order_management;

import com.example.ordermanagement.order_management.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
