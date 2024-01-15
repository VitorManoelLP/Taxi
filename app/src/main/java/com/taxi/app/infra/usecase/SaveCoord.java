package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.domain.Coord;
import com.taxi.app.infra.entity.mapper.CoordMapper;
import com.taxi.app.infra.repository.CoordRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveCoord implements com.taxi.app.application.usecase.persistence.SaveCoord {

    private final CoordRepository coordRepository;

    @Override
    public void save(Coord coord) {
        coordRepository.save(CoordMapper.toEntity(coord));
    }

}
