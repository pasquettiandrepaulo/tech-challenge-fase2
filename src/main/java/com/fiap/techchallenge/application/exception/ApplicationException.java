package com.fiap.techchallenge.application.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -4739777751569062814L;

    private final int code;

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
