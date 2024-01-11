package com.taxi.app.infra.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomValidatorConstraint implements ConstraintValidator<CustomValidator, Object> {

    private final ValidatorFactory validatorFactory;
    private String conditionalProperty;
    private Class<?>[] activateGroups;

    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        conditionalProperty = constraintAnnotation.conditionalProperty();
        activateGroups = constraintAnnotation.activateGroups();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            log.info("Validating object: {}", object);
            return validateProperty(object, constraintValidatorContext);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            log.error("Validation error: {}", ex.getMessage());
            return false;
        }
    }

    private boolean validateProperty(Object object, ConstraintValidatorContext constraintValidatorContext)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        StandardEvaluationContext evalContext = new StandardEvaluationContext(object);
        ExpressionParser parser = new SpelExpressionParser();
        Boolean conditionValue = parser.parseExpression(conditionalProperty).getValue(evalContext, Boolean.class);

        if (Objects.nonNull(conditionValue) && !conditionValue) {

            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Object>> violations = validator.validate(object, activateGroups);

            if (!violations.isEmpty()) {

                constraintValidatorContext.disableDefaultConstraintViolation();

                for (ConstraintViolation<Object> constraintViolation : violations) {
                    constraintValidatorContext.buildConstraintViolationWithTemplate(constraintViolation.getMessage())
                            .addPropertyNode(constraintViolation.getPropertyPath().toString())
                            .addConstraintViolation();
                }

                return false;
            }

        }
        return Boolean.TRUE.equals(conditionValue);
    }

}
