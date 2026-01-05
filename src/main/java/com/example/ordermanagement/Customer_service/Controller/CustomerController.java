package com.example.ordermanagement.Customer_service.Controller;

import com.example.ordermanagement.Customer_service.entity.Customer;
import com.example.ordermanagement.Customer_service.repository.CustomerRepository;
import com.example.ordermanagement.Customer_service.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository repository,
                              CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Integer customerId,
                                   @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    // ✅ GET all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("customer " + customerId + " deleted successfully");
    }

    // ⭐ NEW ENDPOINT  FOR BALANCE CHECK
    @PostMapping("/{customerId}/deduct-balance")
    public ResponseEntity<String> deductBalance(
            @PathVariable Integer customerId,
            @RequestParam Double amount) {

        customerService.deductBalance(customerId, amount);
        return ResponseEntity.ok("Balance deducted successfully");
    }

}
