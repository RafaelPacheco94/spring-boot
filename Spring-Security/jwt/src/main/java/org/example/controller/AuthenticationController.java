package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegistrationDto;
import org.example.entity.User;
import org.example.service.AuthenticationService;
import org.example.util.AuthenticationProviderUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/")
public class AuthenticationController {

    private final AuthenticationProviderUtil authenticationProviderUtil;

    private final AuthenticationService authenticationService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(authenticationProviderUtil.createToken(user));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody @Valid RegistrationDto registrationDto) {
        authenticationService.register(registrationDto);
        return ResponseEntity.ok().build();
    }


}
