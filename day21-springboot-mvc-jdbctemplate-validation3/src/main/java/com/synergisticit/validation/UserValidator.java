package com.synergisticit.validation;

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
        User user = (User) target;
        
        // rejectIfEmptyOrWhitespace(errors, field name, error code, default message if no message is present for error code)
        // if error code matches from a key in message.properties - then it gets error message from message.properties
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "must have username (msg from UserValidator)");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty", "must have email (msg from UserValidator)");
    
        if (user.getMobile().length() < 10) {
            // rejectValue(field name, error code, default message)
            errors.rejectValue("mobile", "user.mobile.length", "must be of 10 characters (msg from UserValidator)");
        }
    }

}
