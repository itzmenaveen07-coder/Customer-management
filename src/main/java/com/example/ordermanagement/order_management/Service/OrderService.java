package com.example.ordermanagement.order_management.Service;

import com.example.ordermanagement.order_management.entity.Customer;
import com.example.ordermanagement.order_management.entity.Order;
import com.example.ordermanagement.order_management.repository.CustomerRepository;
import com.example.ordermanagement.order_management.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;  // call customer service directly

    public Order createOrder(Integer customerId, Double amount, String productName) {

        // ✅ Deduct balance directly
        customerService.deductBalance(customerId, amount);
        log.info("Balance successfully deducted for customer {}", customerId);

        // ✅ Create order
        Order order = new Order();
        order.setAmount(amount);
        order.setProductname(productName);
        order.setOrderStatus("Order Placed");

        // ✅ Attach customer entity (just reference ID is enough)
        Customer customer = customerService.getCustomerById(customerId);
        order.setCustomer(customer);

        log.info("Order created for customer {}", customerId);

        return orderRepository.save(order);
    }

    public Order getorderDetailsById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public List<Order> getAllorders() {
        return orderRepository.findAll();
    }
}
