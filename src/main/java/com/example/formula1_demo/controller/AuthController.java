package com.example.formula1_demo.controller;

import com.example.formula1_demo.DTO.ErrorResponse;
import com.example.formula1_demo.DTO.LoginRequestDTO;
import com.example.formula1_demo.DTO.LoginResponseDTO;
import com.example.formula1_demo.DTO.RegisterRequestDTO;
import com.example.formula1_demo.DTO.RegisterResponseDTO;
import com.example.formula1_demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            RegisterResponseDTO response = authService.register(registerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            RegisterResponseDTO response = authService.registerAdmin(registerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
