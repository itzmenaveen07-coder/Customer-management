package com.example.ordermanagement.order_management;

import lombok.Data;

@Data
public class OrderResponse {
    private Integer orderId;
    private String productName;
    private Double amount;
    private String orderStatus;
    private Integer customerId;
}