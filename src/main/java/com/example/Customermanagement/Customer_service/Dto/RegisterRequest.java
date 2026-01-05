package com.example.Customermanagement.Customer_service.Dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;

}
