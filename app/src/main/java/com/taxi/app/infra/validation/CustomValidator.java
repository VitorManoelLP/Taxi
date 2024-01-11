package com.taxi.app.infra.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Repeatable(CustomValidator.CustomValidators.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@ReportAsSingleViolation
@Constraint(validatedBy = {CustomValidatorConstraint.class})
public @interface CustomValidator {

    String message() default "Field is required.";
    String conditionalProperty();
    Class<?>[] activateGroups();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ANNOTATION_TYPE, TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface CustomValidators {
        CustomValidator[] value();
    }

}
