package com.library.LibraryManagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Constraint(validatedBy = IsbnValidator.class)
public @interface Isbn {
    String message() default "ISBN must be between 8 and 10 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
