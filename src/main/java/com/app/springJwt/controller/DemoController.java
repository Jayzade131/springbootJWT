package com.app.springJwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("hey I am controller");
    }

    @GetMapping("/hiadmin")
    public ResponseEntity<String> hiadmin()
    {
        return ResponseEntity.ok("hey I am admin");
    }

}
