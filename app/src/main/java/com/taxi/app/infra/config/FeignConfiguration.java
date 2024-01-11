package com.taxi.app.infra.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.taxi.app.infra.clients")
public class FeignConfiguration {
}
