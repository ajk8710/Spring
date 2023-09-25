package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Passenger;

@Component
public class PassengerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Passenger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "passenger.firstName.value", "must have first name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "passenger.lastName.value", "must have last name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "passenger.email.value", "must have email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "passenger.phone.value", "must have phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "passenger.gender.value", "must have gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "passenger.dob.value", "must have dob");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificationType", "passenger.identificationType.value", "must have id type");
    
        Passenger passenger = (Passenger) target;
        Period period = Period.between(passenger.getDob(), LocalDate.now());
        // System.out.println(period.getDays());
        if (period.getDays() < 0) {
            errors.rejectValue("dob", "passenger.dob.future", "dob cannot be in future");
        }
    }

}
