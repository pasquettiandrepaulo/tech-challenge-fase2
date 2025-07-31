package com.fiap.techchallenge.adapter.in.controller.dto.response.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8358341736929601292L;

    @Schema(example = "400")
    private int code;

    @Schema(example = "BUSINESS_EXCEPTION")
    private String message;

    @Schema(example = "Message error details")
    private String details;

}