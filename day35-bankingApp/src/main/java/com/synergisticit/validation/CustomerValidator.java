package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Customer;

@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        Customer customer = (Customer) target;
        
        Period period = Period.between(customer.getCustomerDob(), LocalDate.now());
        System.out.println(period.getYears());
        if (period.getYears() < 18) {
            errors.rejectValue("customerDob", "customer.customerDob.value", "Age should not be less than 18 (msg from Validator)");
        }
    }
    
}
