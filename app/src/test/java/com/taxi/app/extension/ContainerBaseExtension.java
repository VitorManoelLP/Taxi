package com.taxi.app.extension;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.taxi.app.infra.config.HibernateCustomizer;
import com.taxi.app.infra.config.HibernateValidatorConfig;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Testcontainers
@Transactional
@DirtiesContext
@Rollback
@WithMockUser
@Import({
        HibernateValidatorConfig.class,
        HibernateCustomizer.class
})
public abstract class ContainerBaseExtension {

    @Autowired
    private EntityManager em;

    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withUsername("postgres")
            .withPassword("postgres")
            .withEnv(Map.of("PGDATA", "/var/lib/postgresql/data"))
            .withTmpFs(Map.of("/var/lib/postgresql/data", "rw"));

    public EntityManager getEm() {
        return em;
    }

}
