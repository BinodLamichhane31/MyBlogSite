package com.example.myblogsite.controller;

import com.example.myblogsite.entity.User;
import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.security.JwtTokenHelper;
import com.example.myblogsite.service.UserService;
import com.example.myblogsite.shared.pojo.JwtAuthRequest;
import com.example.myblogsite.shared.pojo.JwtAuthResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createJwtToken(@RequestBody JwtAuthRequest authRequest) throws Exception {
            this.authenticate(authRequest.getUsername(), authRequest.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            String token = this.jwtTokenHelper.generateToken(userDetails);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(token);
            jwtAuthResponse.setUser(this.mapper.map((User) userDetails, UserPojo.class));
            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserPojo> registerUser(@Valid @RequestBody UserPojo userPojo){
        UserPojo registeredUser = this.userService.registerUser(userPojo);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
