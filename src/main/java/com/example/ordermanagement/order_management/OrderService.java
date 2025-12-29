package com.example.ordermanagement.order_management;
import com.example.ordermanagement.order_management.Order;
import com.example.ordermanagement.order_management.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public OrderService(CustomerRepository customerRepository,
                        OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Integer customerId,
                             Double amount,
                             String productName) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setAmount(amount);
        order.setProductname(productName);
        order.setOrderStatus("Order Placed");
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

}

