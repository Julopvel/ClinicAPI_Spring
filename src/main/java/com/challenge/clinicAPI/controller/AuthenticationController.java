package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.infra.security.JwtToken;
import com.challenge.clinicAPI.infra.security.TokenService;
import com.challenge.clinicAPI.model.user.User;
import com.challenge.clinicAPI.model.user.UserAuthenticationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<JwtToken> userAuthentication(@RequestBody @Valid UserAuthenticationData userAuthenticationData){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userAuthenticationData.username(),
                userAuthenticationData.password());

        System.out.println(userAuthenticationData.username() + " " +
                userAuthenticationData.password());

        var userAuthenticated = authenticationManager.authenticate(authentication);

        var jwtToken = tokenService.generateToken((User) userAuthenticated.getPrincipal());

        System.out.println(jwtToken);

        return ResponseEntity.ok(new JwtToken(jwtToken));
    }
}
