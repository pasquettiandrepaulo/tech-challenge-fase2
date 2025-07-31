package com.fiap.techchallenge.domain.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1303900059485713654L;

    public BusinessException(String message) {
        super(message);
    }
}
