package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Branch;

@Component
public class BranchValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Branch.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName", "branch.branchName.value", "must have name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress", "branch.branchAddress.value", "must have address");

    }

}
