package com.taxi.app.infra.config.security;

import java.util.List;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.taxi.app.domain.enums.Roles;
import com.taxi.app.infra.usecase.AccountManager;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfiguration {

    private static final String AUTH_SIGN_UP = "/api/auth/sign-up";
    private static final String AUTH_SIGN_IN = "/api/auth/sign-in";
    private static final String IS_VALID_TOKEN = "/api/auth/is-valid-token";
    private static final String OUTBOX = "/api/outbox";
    private static final String OUTBOX_ERROR = "/api/outbox/error";

    private final AccountManager accountManager;

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

        final AuthenticationManagerBuilder sharedObject = httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class);

        sharedObject
                .userDetailsService(accountManager)
                .passwordEncoder(password());

        final AuthenticationManager manager = sharedObject.build();

        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, AUTH_SIGN_UP),
                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, AUTH_SIGN_IN),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, IS_VALID_TOKEN)
                        )
                        .permitAll()
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, OUTBOX),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, OUTBOX_ERROR)
                        )
                        .hasRole(Roles.ADMIN.getRole())
                        .anyRequest()
                        .authenticated())
                .authenticationManager(manager)
                .addFilter(new AuthFilter(manager, accountManager))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    public static BCryptPasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(SecurityConstraint.ROLE_ALL_ROLES));
        configuration.setAllowedMethods(List.of(SecurityConstraint.ROLE_ALL_ROLES));
        configuration.setAllowedHeaders(List.of(SecurityConstraint.ROLE_ALL_ROLES));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/".concat(SecurityConstraint.ROLE_ALL_AUTHENTICATED_USERS), configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
