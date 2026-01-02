package com.example.ordermanagement.order_management.repository;
import com.example.ordermanagement.order_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
