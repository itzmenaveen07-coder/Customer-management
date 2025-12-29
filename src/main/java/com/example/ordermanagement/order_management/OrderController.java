package com.example.ordermanagement.order_management;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{customerId}")
    public OrderResponse createOrder(
            @PathVariable Integer customerId,
            @RequestBody OrderRequest request) {

        // 1️⃣ Call service (DB work happens here)
        Order order = orderService.createOrder(
                customerId,
                request.getAmount(),
                request.getProductName()
        );

        // 2️⃣ Convert Entity → Response DTO
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setProductName(order.getProductname());
        response.setAmount(order.getAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setCustomerId(order.getCustomer().getCustomerId());

        // 3️⃣ Return clean response
        return response;
    }
}



