package com.example.Customermanagement.Customer_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "Product_Name")
    private String Productname;

    private String orderStatus;
    private Double amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // 🔥 FOREIGN KEY mapping
    @ManyToOne
    @JoinColumn(
            name = "customer_id",           // FK column in orders table
            referencedColumnName = "customer_id"
    )
    @JsonIgnore
    private Customer customer;
}
