package com.example.bloggersite01.Controller;

import com.example.bloggersite01.Entity.User;
import com.example.bloggersite01.Repository.UserRepository;
import com.example.bloggersite01.Security.jwt.JwtUtils;
import com.example.bloggersite01.payload.JwtResponse;
import com.example.bloggersite01.payload.LoginRequest;
import com.example.bloggersite01.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Username is already taken"));
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email is already in use"));
        }
        User newuser = new User();
        newuser.setUsername(user.getUsername());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));
        newuser.setEmail(user.getEmail());
        userRepository.save(newuser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
       );
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String jwt = jwtUtils.generateJwtToken(authentication);
       User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
       return  ResponseEntity.ok().body(new JwtResponse(jwt,user.getId(),user.getUsername(),user.getEmail()));
   }
}
