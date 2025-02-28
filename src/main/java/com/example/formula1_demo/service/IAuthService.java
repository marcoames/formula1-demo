package com.example.formula1_demo.service;

import com.example.formula1_demo.DTO.LoginRequestDTO;
import com.example.formula1_demo.DTO.LoginResponseDTO;
import com.example.formula1_demo.DTO.RegisterRequestDTO;
import com.example.formula1_demo.DTO.RegisterResponseDTO;

public interface IAuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequest);

    RegisterResponseDTO register(RegisterRequestDTO registerRequest);

    RegisterResponseDTO registerAdmin(RegisterRequestDTO registerRequest);
}
