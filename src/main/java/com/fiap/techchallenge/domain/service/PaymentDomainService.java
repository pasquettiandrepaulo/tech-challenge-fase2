package com.fiap.techchallenge.domain.service;

import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import com.fiap.techchallenge.domain.document.OrderDocument;

public interface PaymentDomainService {

    void validateCreatedPaymentAndUpdateOrder(OrderDocument orderDocument, PaymentGatewayResponseDTO paymentGatewayResponseDTO);

    void validatePaymentStatusOrder(OrderDocument orderDocument);

    void validatePayedPaymentAndUpdateOrder(OrderDocument orderDocument, PaymentGatewayResponseDTO paymentGateWayResponseDTO);

}