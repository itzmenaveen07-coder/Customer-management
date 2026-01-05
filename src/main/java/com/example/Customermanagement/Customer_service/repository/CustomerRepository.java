package com.example.Customermanagement.Customer_service.repository;

import com.example.Customermanagement.Customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
