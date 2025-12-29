package com.example.ordermanagement.order_management;
import lombok.Data;

@Data
public class OrderRequest {
    private Double amount;
    private String productName;
}