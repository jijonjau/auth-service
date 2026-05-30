package com.jijonjau.auth_service.application.dto;

import java.util.UUID;

public record RegisterResponse(
        UUID userUuid,
        String email,
        String message
) {}