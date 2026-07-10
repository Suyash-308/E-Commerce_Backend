package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.dto.AuthResponse;
import com.suyash.ecommerce_backend.dto.LoginRequest;
import com.suyash.ecommerce_backend.dto.RegisterRequest;
import com.suyash.ecommerce_backend.enumPackage.Role;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.repository.UserRepository;
import com.suyash.ecommerce_backend.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // REGISTER
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN (NOW RETURNS JWT)
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
