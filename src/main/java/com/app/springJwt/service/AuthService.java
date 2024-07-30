package com.app.springJwt.service;

import com.app.springJwt.Repository.UserRepository;
import com.app.springJwt.model.AuthenticationtResponse;
import com.app.springJwt.model.Role;
import com.app.springJwt.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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

        user.setRole(Role.USER);

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        return  new AuthenticationtResponse(token,user);

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

        return  new AuthenticationtResponse(token,user);
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount=	userRepository.findByRole(Role.ADMIN);
        if(null == adminAccount)
        {
           User user=new User();
           user.setFirstName("Jay");
           user.setLastName("zade");
           user.setUserName("admin123");
           user.setPassword(passwordEncoder.encode("admin@123"));
           user.setRole(Role.ADMIN);
           userRepository.save(user);

        }
    }
}
