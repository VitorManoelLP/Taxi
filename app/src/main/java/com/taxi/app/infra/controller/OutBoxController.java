package com.taxi.app.infra.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.app.dto.OutBoxResponse;
import com.taxi.app.infra.entity.mapper.OutBoxMapper;
import com.taxi.app.infra.repository.OutBoxRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/outbox")
public class OutBoxController {

    private final OutBoxRepository outBoxRepository;

    @GetMapping
    public ResponseEntity<Page<OutBoxResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(outBoxRepository.findAll(pageable).map(OutBoxMapper::toResponse));
    }

    @GetMapping("/error")
    public ResponseEntity<Page<OutBoxResponse>> findAllOnlyErrors(Pageable pageable) {
        return ResponseEntity.ok(outBoxRepository.findAllByHasErrorTrue(pageable).map(OutBoxMapper::toResponse));
    }

}
