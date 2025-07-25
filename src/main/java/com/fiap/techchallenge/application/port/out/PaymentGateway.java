package com.fiap.techchallenge.application.port.out;

import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;

import java.math.BigDecimal;

public interface PaymentGateway {

    PaymentGatewayResponseDTO generatePayment(BigDecimal amount);

    PaymentGatewayResponseDTO confirmPayment(String paymentId);

}
