package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Employee;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "employee.name", "must have name (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "employee.deignation", "must have designation (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "employee.salary", "must have salary (msg from Validator)");
    
    }

}
