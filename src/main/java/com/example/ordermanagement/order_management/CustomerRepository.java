package com.example.ordermanagement.order_management;
import com.example.ordermanagement.order_management.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
