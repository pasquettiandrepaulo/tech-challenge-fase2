package com.fiap.techchallenge.adapter.out.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class PaymentGatewayResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3821026410772398044L;

    private String status;

    @JsonAlias("mensagem")
    private String message;

    @JsonAlias("codigo_autorizacao")
    private String authorizationCode;

    @JsonAlias("forma_pagamento")
    private String paymentType;

    @JsonAlias("pagamento_id")
    private String paymentId;

    @JsonAlias("qr_code")
    private String qrCode;

}
