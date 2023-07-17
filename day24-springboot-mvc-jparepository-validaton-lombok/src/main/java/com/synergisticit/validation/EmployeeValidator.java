package com.synergisticit.validation;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Employee;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

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
        
        Employee employee = (Employee) target;
        if (employee.getName().length() < 2) {
            errors.rejectValue("name", "employee.name.length", "must be legnth 2 to 20 (msg from Validator");
        }
        
        if (employee.getSalary() < 70000) {
            errors.rejectValue("salary", "employee.salary.value", "salary should not be < 70000 (msg from Validator");
        }
        
//        Duration duration = Duration.between(LocalDate.now(), employee.getDob());
//        System.out.println("duration: " + duration);
//        
//         if (employee.getDob().getYear() < 21) {
//            errors.rejectValue("dob", "employee.dob.value", "Age should not be less than 21 (msg from Validator");
//        }
        
        if (employee.getGender() == null) {
            errors.rejectValue("gender", "employee.gender.value", "gender is required field (msg from Validator)");
        }
        
        if (employee.getCitizenship() == null) {
            errors.rejectValue("citizenship", "employee.citizenship.value", "citizenship is required field (msg from Validator)");
        }
        
        if (employee.getHobbies().length < 1) {
            errors.rejectValue("hobbies", "employee.hobbies.value", "hobbies are required field (msg from Validator)");
        }
        
        if (employee.getSpokenLanguages().size() < 1) {
            errors.rejectValue("spokenLanguages", "employee.spokenLanguages.value", "languages are required field (msg from Validator)");
        }
        
        if (employee.getSkills().size() < 1) {
            errors.rejectValue("skills", "employee.skills.value", "skills are required field (msg from Validator)");
        }
        
        if (employee.getPhoneNumber().length() != 10) {
            errors.rejectValue("phoneNumber", "employee.phoneNumber.value", "should be 10 digits (msg from Validator)");
        }
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        jakarta.validation.Validator validator = validatorFactory.getValidator();
        
        System.out.println("Following are the violations with their invalid value:");
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        for (ConstraintViolation<Employee> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getInvalidValue());
        }
        
    }

}
