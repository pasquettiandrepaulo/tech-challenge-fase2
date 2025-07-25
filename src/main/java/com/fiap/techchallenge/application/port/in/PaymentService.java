package com.fiap.techchallenge.application.port.in;

import com.fiap.techchallenge.adapter.in.controller.dto.response.payment.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO getPayment(String orderId);

    PaymentResponseDTO payCheckout(String orderId);

}
