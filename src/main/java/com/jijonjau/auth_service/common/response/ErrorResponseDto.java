package com.jijonjau.auth_service.common.response;

public record ErrorResponseDto(
        String message,
        int status
) {}