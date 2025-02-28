package com.example.formula1_demo.service;

import com.example.formula1_demo.DTO.LoginRequestDTO;
import com.example.formula1_demo.DTO.LoginResponseDTO;
import com.example.formula1_demo.DTO.RegisterRequestDTO;

public interface IAuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequest);

    LoginResponseDTO register(RegisterRequestDTO registerRequest);

    LoginResponseDTO registerAdmin(RegisterRequestDTO registerRequest);
}
