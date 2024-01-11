package com.taxi.app.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpringExceptionHandler {

    @ExceptionHandler({ FeignException.class, FeignException.FeignClientException.class })
    public ResponseEntity<?> handleFeignException(final FeignException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.status()).body(ex.getMessage());
    }

}
