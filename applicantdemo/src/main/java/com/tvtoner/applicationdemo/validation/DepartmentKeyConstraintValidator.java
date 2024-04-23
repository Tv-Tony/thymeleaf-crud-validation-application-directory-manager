package com.tvtoner.applicationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

/**
 * Backend implementation of the annotation
 */
public class DepartmentKeyConstraintValidator implements ConstraintValidator<DepartmentKey, String> {

    private String[] allowedDepartmentPrefixes;

    /**
     * We set the array to the allowed prefix that we receive from the annotation or if nothing is given the default one
     * @param constraintAnnotation its is the DepartmentKey annotation
     */
    @Override
    public void initialize(DepartmentKey constraintAnnotation) {
       this.allowedDepartmentPrefixes = constraintAnnotation.value();
    }

    /**
     * Simple logic to see data given from the form matches one of the set prefixes, if true everything proceeds, if false
     * error message is displayed in the html file
     * @param theCode the value to be validated
     * @param constraintValidatorContext given by the framework for additional context during validation
     * @return if prefix matches, returns true and allows data to be validated
     */
    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        if (theCode == null) {
            return false; // Null values are invalid
        }

        for (String prefix : allowedDepartmentPrefixes) {
            if (Objects.equals(prefix, theCode)) {
                return true; // Code starts with one of the allowed prefixes
            }
        }
        return false; // Code does not start with any allowed prefix
    }


}
