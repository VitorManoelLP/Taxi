package com.taxi.app.infra.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ValidatorFactory;

@Configuration
public class HibernateValidatorConfig {

    @Bean
    @ConditionalOnMissingBean(ValidatorFactory.class)
    public ValidatorFactory validatorFactory() {
        return new LocalValidatorFactoryBean();
    }

}
