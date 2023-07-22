package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field name", "custom error code", "default message if error code not defined in messages.properties")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "user.userId.empty", "must have ID (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "must have username (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "must have password (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty", "must have email (msg from Validator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "user.mobile.empty", "must have mobile (msg from Validator)");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "user.roles.empty", "must have roles (msg from Validator)");
        
        User user = (User) target;
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "user.email.invalid", "must contain @ (msg from Validator)");
        }
    }

}
