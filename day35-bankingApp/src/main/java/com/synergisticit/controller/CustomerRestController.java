package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Customer;
import com.synergisticit.service.CustomerService;
import com.synergisticit.validation.CustomerValidator;

import jakarta.validation.Valid;

@RestController
public class CustomerRestController {
    
    @Autowired CustomerService customerService;
    @Autowired CustomerValidator customerValidator;
    
    // http://localhost:8080/customer?customerId=1
    @RequestMapping(value="customer", method=RequestMethod.GET)
    public ResponseEntity<?> getCustomerById(@RequestParam Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<String>("Customer not found with id=" + customerId, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
        }
    }
    
    // http://localhost:8080/customer/all
    @RequestMapping(value={"customer/all", "customers"}, method=RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/customer/create - save if id not already exist
    /*
{
    "customerId": 10,
    "customerName": "Sonny",
    "customerGender": "M",
    "customerDob": "1991-07-01",
    "customerMobileNum": "1231231234",
    "customerAddress": {
        "addressLine1": "Baller St",
        "addressLine2": "",
        "city": "Baller City",
        "state": "WA",
        "country": "USA",
        "zipCode": "77777"
    },
    "realId": "123",
    "user": {
        "userId": 7
    }
}
     */
    @RequestMapping(value="customer/create", method=RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult br) {
        customerValidator.validate(customer, br);
        
        Long customerId = customer.getCustomerId();
        if (customerService.existById(customerId)) {
            return new ResponseEntity<String>("Customer already exists with id=" + customerId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            customerService.saveCustomer(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
        }
    }

    // http://localhost:8080/customer/update - save if id already exists
    @RequestMapping(value="customer/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer, BindingResult br) {
        customerValidator.validate(customer, br);
        
        Long customerId = customer.getCustomerId();
        if (!customerService.existById(customerId)) {
            return new ResponseEntity<String>("Customer does not exist with id=" + customerId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            customerService.saveCustomer(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
        }
    }
    
    // http://localhost:8080/customer/delete?customerId=11
    @RequestMapping(value="customer/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomerById(@RequestParam Long customerId) {
        if (customerService.existById(customerId)) {
            customerService.deleteCustomerById(customerId);
            return new ResponseEntity<String>("Customer deleted with id=" + customerId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Customer does not exist with id=" + customerId, HttpStatus.NOT_FOUND);
        }
    }
    
}
