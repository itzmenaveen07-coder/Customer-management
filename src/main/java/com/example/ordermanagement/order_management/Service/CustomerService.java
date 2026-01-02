package com.example.ordermanagement.order_management.Service;

import com.example.ordermanagement.order_management.entity.Customer;
import com.example.ordermanagement.order_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public Customer updateCustomer(Integer customerId, Customer updatedCustomer)
    {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (updatedCustomer.getName() != null)
            existingCustomer.setName(updatedCustomer.getName());

        if (updatedCustomer.getEmail() != null)
            existingCustomer.setEmail(updatedCustomer.getEmail());

        if (updatedCustomer.getPhone() != null)
            existingCustomer.setPhone(updatedCustomer.getPhone());

        if (updatedCustomer.getCity() != null)
            existingCustomer.setCity(updatedCustomer.getCity());

        if (updatedCustomer.getPincode() != null)
            existingCustomer.setPincode(updatedCustomer.getPincode());

        return customerRepository.save(existingCustomer);


    }
    // ✅ Get customer by ID
    public Customer getCustomerById(Integer customerId)
    {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    }

    // ✅ Get all customers
    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }
    public void deleteCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }
    public void deductBalance(Integer customerId, Double amount)
    {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));;

        if (customer.getBalance() == null)
        {
            customer.setBalance(0.0);
        }

        if (customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        customer.setBalance(customer.getBalance() - amount);
        customerRepository.save(customer);
    }

}
