package com.taxi.app.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxi.app.domain.Coord;

@FeignClient(name = "geolocation-python", url = "${geolocation-url:http://localhost:5001}")
public interface GeolocationPythonClient {

    @GetMapping(value = "/calculate")
    Coord calculate(@RequestParam String cep);

}
