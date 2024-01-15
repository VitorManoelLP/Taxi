package com.taxi.app.infra.config.security.token;

import java.security.Key;
import java.util.Base64;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.security.Keys;

@Component
public class KeyResolver extends SigningKeyResolverAdapter {

    private static final String JWT_KEY = "~R5e^uY8N#@k!pTz%Wb4@hB@f#vGZaQ2LdK!+Uc3_1fXn2*d";

    @Override
    public Key resolveSigningKey(JwsHeader header, Claims claims) {
        return Keys.hmacShaKeyFor(generateSafeSecret());
    }

    public static byte[] generateSafeSecret() {
        byte[] key = JWT_KEY.getBytes();
        var encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encode(key);
    }

}
