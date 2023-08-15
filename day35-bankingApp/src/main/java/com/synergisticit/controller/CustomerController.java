package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.CustomerValidator;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
    
    @Autowired CustomerService customerService;
    @Autowired UserService userService;
    @Autowired CustomerValidator customerValidator;
    
    // This class-wise validator causes error on update call, trying to validate selectedUser as customer.
    // Thus have validator in saveCustomer method, instead of having class-wise.
    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    //     binder.addValidators(customerValidator);
    // }
    
    @RequestMapping("customerForm")
    public String customerForm(Customer customer, Model model) {
        modelData(model);
        return "customerForm";
    }
    
    @RequestMapping("saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        customerValidator.validate(customer, br);
        
        if (!br.hasErrors()) {
            customerService.saveCustomer(customer);
            // do not need to call modelData(model) because it's in customerForm method
            return "redirect:customerForm";  // redirect to url customerForm which calls customerForm method
        }
        
        modelData(model);
        return "customerForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateCustomer")
    public String updateCustomer(Customer customer, Model model) {  // Customer object created with only id is passed in by request param
        Customer retrievedCustomer = customerService.getCustomerById(customer.getCustomerId());
        model.addAttribute("retrievedCustomer", retrievedCustomer);

        User selectedUser = retrievedCustomer.getUser();
        model.addAttribute("selectedUser", selectedUser);
        
        modelData(model);
        return "customerForm";
    }
    
    @RequestMapping("deleteCustomer")
    public String deleteCustomer(Customer customer, Model model) {
        customerService.deleteCustomerById(customer.getCustomerId());
        
        modelData(model);
        return "redirect:customerForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("ListofAllUsers", userService.getAllUsers());
    }
}
