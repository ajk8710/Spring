package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

    @Autowired CustomerRepository customerRepository;
    
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public boolean existById(Long customerId) {
        return customerRepository.existsById(customerId);
    }

}
