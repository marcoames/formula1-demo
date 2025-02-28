package com.example.formula1_demo.service;

import com.example.formula1_demo.DTO.LoginRequestDTO;
import com.example.formula1_demo.DTO.LoginResponseDTO;
import com.example.formula1_demo.DTO.RegisterRequestDTO;
import com.example.formula1_demo.entity.Role;
import com.example.formula1_demo.entity.User;
import com.example.formula1_demo.repository.UserRepository;
import com.example.formula1_demo.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new LoginResponseDTO(user.getEmail(), token);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public LoginResponseDTO register(RegisterRequestDTO registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
            newUser.setEmail(registerRequest.email());
            newUser.setRole(Role.USER);
            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return new LoginResponseDTO(newUser.getEmail(), token);
        }
        throw new RuntimeException("User already exists");
    }

    public LoginResponseDTO registerAdmin(RegisterRequestDTO registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
            newUser.setEmail(registerRequest.email());
            newUser.setRole(Role.ADMIN);
            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return new LoginResponseDTO(newUser.getEmail(), token);
        }
        throw new RuntimeException("User already exists");
    }
}
