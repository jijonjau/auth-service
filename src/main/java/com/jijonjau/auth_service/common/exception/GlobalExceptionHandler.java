package com.jijonjau.auth_service.common.exception;

import com.jijonjau.auth_service.common.response.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(ex.getMessage(), 400));
    }
}