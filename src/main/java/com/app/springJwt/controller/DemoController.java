package com.app.springJwt.controller;

import com.app.springJwt.Repository.UserRepository;
import com.app.springJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName)
    {
        User user = userRepository.findByUserName(userName).orElseThrow();
    
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/{userName}")
    public ResponseEntity<User> getadmin(@PathVariable String userName)
    {
       User user= userRepository.findByUserName(userName).orElseThrow();

       return  ResponseEntity.ok(user);
    }

}
