package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Role;

@Component
public class RoleValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
     // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field name", "custom error code", "default message if error code not defined in messages.properties")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "role.roleId.empty", "must have ID (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "role.name.empty", "must have name (msg from Validator)");
    }

}
