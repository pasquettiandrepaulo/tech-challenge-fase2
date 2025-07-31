package com.fiap.techchallenge.config.handler;

import com.fiap.techchallenge.adapter.in.controller.dto.response.exception.ErrorResponseDTO;
import com.fiap.techchallenge.application.enumerate.ApplicationRuleException;
import com.fiap.techchallenge.application.exception.ApplicationException;
import com.fiap.techchallenge.domain.enumerate.DomainException;
import com.fiap.techchallenge.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), DomainException.BUSINESS_EXCEPTION.name(), ex.getMessage()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDTO> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(ex.getCode()).body(new ErrorResponseDTO(ex.getCode(), ApplicationRuleException.APPLICATION_EXCEPTION.name(), ex.getMessage()));
    }

}
