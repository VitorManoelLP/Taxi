package com.taxi.app.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RideStatusConverter implements AttributeConverter<RideStatus, Long> {
    @Override
    public Long convertToDatabaseColumn(RideStatus attribute) {
        return attribute.getId();
    }

    @Override
    public RideStatus convertToEntityAttribute(Long dbData) {
        return RideStatus.getType(dbData);
    }
}
