package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.util.AuthenticationProviderUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtTestController {

    private final AuthenticationProviderUtil authenticationProviderUtil;

    @RequestMapping(value = "/test")
    public ResponseEntity<User> signUp(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(user);
    }
}
