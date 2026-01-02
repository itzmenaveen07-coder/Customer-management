package com.example.ordermanagement.order_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // ROLE_ADMIN, ROLE_CUSTOMER

    private boolean enabled = true;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

