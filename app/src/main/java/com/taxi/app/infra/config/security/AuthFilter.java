package com.taxi.app.infra.config.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.taxi.app.infra.config.security.token.JwtTokenValidator;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.usecase.AccountManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends BasicAuthenticationFilter {

    private final AccountManager accountManager;

    public AuthFilter(AuthenticationManager authenticationManager, AccountManager accountManager) {
        super(authenticationManager);
        this.accountManager = accountManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final String header = request.getHeader("Authorization");

        if (Objects.isNull(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication = getAuthentication(request, header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String header) {
        final String token = header.replace("Bearer ", "");
        final String username = JwtTokenValidator.getUsername(token);
        final AccountEntity account = (AccountEntity) accountManager.loadUserByUsername(username);
        if (token.isEmpty() || !JwtTokenValidator.isValidToken(token, account)) {
            return null;
        }
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                account.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return auth;
    }
}
