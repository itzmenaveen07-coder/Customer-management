package com.example.ordermanagement.Customer_service.Controller;

import com.example.ordermanagement.Customer_service.entity.Order;
import com.example.ordermanagement.Customer_service.Dto.OrderRequest;
import com.example.ordermanagement.Customer_service.Dto.OrderResponse;
import com.example.ordermanagement.Customer_service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{customerId}")
    public ResponseEntity<OrderResponse> createOrder(
            @PathVariable Integer customerId,
            @RequestBody OrderRequest request) {

        try {
            Order order = orderService.createOrder(customerId, request.getAmount(), request.getProductName());

            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrderId());
            response.setProductName(order.getProductname());
            response.setAmount(order.getAmount());
            response.setOrderStatus(order.getOrderStatus());
            response.setCustomerId(order.getCustomer().getCustomerId());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new OrderResponse() {{
                        setOrderStatus(e.getMessage());
                    }});
        }
    }

    @GetMapping("/{orderId}")
    public OrderResponse getorderDetailsById(@PathVariable Integer orderId) {
        Order order = orderService.getorderDetailsById(orderId);
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setProductName(order.getProductname());
        response.setAmount(order.getAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setCustomerId(order.getCustomer() != null ? order.getCustomer().getCustomerId() : null);
        response.setCustomerName(order.getCustomer() != null ? order.getCustomer().getName() : null);
        return response;
    }

    @GetMapping()
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderService.getAllorders();

        return orders.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrderId());
            response.setProductName(order.getProductname());
            response.setAmount(order.getAmount());
            response.setOrderStatus(order.getOrderStatus());
            response.setCustomerId(order.getCustomer() != null ? order.getCustomer().getCustomerId() : null);
            response.setCustomerName(order.getCustomer() != null ? order.getCustomer().getName() : null);
            return response;
        }).toList();
    }

}



