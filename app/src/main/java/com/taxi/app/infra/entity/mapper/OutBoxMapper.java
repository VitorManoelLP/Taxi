package com.taxi.app.infra.entity.mapper;

import com.taxi.app.dto.OutBoxResponse;
import com.taxi.app.infra.entity.OutBoxEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OutBoxMapper {

    public OutBoxResponse toResponse(OutBoxEntity outBoxEntity) {
        return new OutBoxResponse(outBoxEntity.getId(), outBoxEntity.getRequestedBy(), outBoxEntity.isSent(), outBoxEntity.getExchange(), outBoxEntity.getErrorMessage());
    }

}
