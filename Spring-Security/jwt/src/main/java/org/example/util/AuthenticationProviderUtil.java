package org.example.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.dto.AuthenticationRequestDto;
import org.example.entity.User;
import org.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class AuthenticationProviderUtil {

    @Value("${spring.security.jwt.secret.key: default-key}")
    private String key;

    @Value("${spring.security.jwt.expiration.date: 3600000}")
    private long expirationDate; // 1 hours in millis

    private final AuthenticationService authenticationService;

    public AuthenticationProviderUtil(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void encodeKey() {
        key = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationDate))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Authentication validateToken(String token) {
        String id = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        User user = authenticationService.findById(id);

        return new UsernamePasswordAuthenticationToken(user,
                null, Collections.emptyList());
    }


    public Authentication validateCredentials(AuthenticationRequestDto authenticationRequestDto) {
        User user = authenticationService.authenticate(authenticationRequestDto);
        return new UsernamePasswordAuthenticationToken(user,
                null, Collections.emptyList());
    }
}
