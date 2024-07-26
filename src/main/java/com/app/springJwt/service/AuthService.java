package com.app.springJwt.service;

import com.app.springJwt.Repository.UserRepository;
import com.app.springJwt.model.AuthenticationtResponse;
import com.app.springJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;



    public AuthenticationtResponse register(User request)
    {
        User user=new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        return  new AuthenticationtResponse(token);

    }

    public  AuthenticationtResponse authenticate(User request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()
                )
        );

        User user = userRepository.findByUserName(request.getUsername()).orElseThrow();
        String token=jwtService.generateToken(user);

        return  new AuthenticationtResponse(token);
    }
}
