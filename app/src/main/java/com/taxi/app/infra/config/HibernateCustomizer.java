package com.taxi.app.infra.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class HibernateCustomizer  implements HibernatePropertiesCustomizer {

    private final ValidatorFactory validatorFactory;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("javax.persistence.validation.factory", validatorFactory);
    }

}
