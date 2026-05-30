package com.jijonjau.auth_service.application.services;

import com.jijonjau.auth_service.application.dto.RegisterRequest;
import com.jijonjau.auth_service.application.dto.RegisterResponse;
import com.jijonjau.auth_service.common.exception.EmailAlreadyExistsException;
import com.jijonjau.auth_service.domain.entity.User;
import com.jijonjau.auth_service.domain.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {

        validatePasswords(request);

        validateEmailAvailability(request.email());

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .enabled(false)
                .accountLocked(false)
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getUserUuid(),
                savedUser.getEmail(),
                "User registered successfully"
        );

        // email verification logic later
    }

    private void validatePasswords(RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    private void validateEmailAvailability(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
    }
}