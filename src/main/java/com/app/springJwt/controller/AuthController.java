package com.app.springJwt.controller;

import com.app.springJwt.model.AuthenticationtResponse;
import com.app.springJwt.model.User;
import com.app.springJwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationtResponse> register(@RequestBody User request)
    {
            return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationtResponse> login(@RequestBody User request)
    {
        return  ResponseEntity.ok(authService.authenticate(request));
    }


}
