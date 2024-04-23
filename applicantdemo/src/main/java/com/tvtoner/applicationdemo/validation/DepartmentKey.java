package com.tvtoner.applicationdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraint defines the class that we implement on how to define if the given department key is valid
 * Target is where we can sue the annotation, in this case in Methods and Fields/Parameters
 * Retention defines that we retain this annotation and process it during runtime
 */
@Constraint(validatedBy = DepartmentKeyConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentKey {

    // Field to where we can define what the value is, it is an array, so we can have multiple Department keys, by default its DEV
    public String[] value() default {"DEV"};

    // The message that will be given if the key from the form doesn't match the ones set from the annotation
    public String message() default "Invalid Department Key";

    // No grouping constraints so we set default groups
    public Class<?>[] groups() default {};

    // We won't use payloads so we set default payloads
    public Class<? extends Payload>[] payload() default{};
}
