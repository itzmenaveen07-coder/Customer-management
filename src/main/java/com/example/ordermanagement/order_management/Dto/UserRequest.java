package com.example.ordermanagement.order_management.Dto;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String role;
}
