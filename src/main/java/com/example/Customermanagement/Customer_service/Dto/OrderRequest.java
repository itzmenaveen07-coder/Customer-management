package com.example.Customermanagement.Customer_service.Dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Double amount;
    private String productName;
}