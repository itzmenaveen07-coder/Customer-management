package com.example.ordermanagement.order_management.repository;

import com.example.ordermanagement.order_management.entity.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
}
