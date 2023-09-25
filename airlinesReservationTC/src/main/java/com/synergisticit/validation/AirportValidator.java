package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airport;

@Component
public class AirportValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Airport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCity", "airport.airportCity.value", "must have airport city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCode", "airport.airportCode.value", "must have airport code");
    }

}
