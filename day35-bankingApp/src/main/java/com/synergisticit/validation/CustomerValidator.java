package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "customer.customerName.value", "must have name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerGender", "customer.customerGender.value", "must have gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerDob", "customer.customerDob.value", "must have date of birth");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerMobileNum", "customer.customerMobileNum.value", "must have mobile");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress", "customer.customerAddress.value", "must have address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "realId", "customer.realId.value", "must have realId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "customer.user.value", "must designate a user");
        
        Customer customer = (Customer) target;
        
        Period period = Period.between(customer.getCustomerDob(), LocalDate.now());
        // System.out.println(period.getYears());
        if (period.getYears() < 18) {
            errors.rejectValue("customerDob", "customer.customerDob.range", "Age should not be less than 18");
        }
    }
    
}
