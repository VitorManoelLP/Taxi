package com.taxi.app.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, Long> {
    @Override
    public Long convertToDatabaseColumn(AccountType attribute) {
        return attribute.getId();
    }

    @Override
    public AccountType convertToEntityAttribute(Long dbData) {
        return AccountType.getType(dbData);
    }
}
