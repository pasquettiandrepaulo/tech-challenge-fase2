package com.fiap.techchallenge.domain.service;

import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import com.fiap.techchallenge.domain.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentDomainServiceImpl implements PaymentDomainService {

    private static final String STATUS_PAYED = "PAYED";
    private static final String STATUS_CREATED = "CREATED";
    private static final String PAYMENT_STATUS_MESSAGE = "Payment status invalid must be [%s]";

    @Override
    public void validateCreatedPaymentAndUpdateOrder(OrderDocument orderDocument, PaymentGatewayResponseDTO paymentGatewayResponseDTO) {
        if (!STATUS_CREATED.equalsIgnoreCase(paymentGatewayResponseDTO.getStatus())) {
            throw new BusinessException(PAYMENT_STATUS_MESSAGE.formatted(STATUS_CREATED));
        }
        orderDocument.setPaymentId(paymentGatewayResponseDTO.getPaymentId());
        orderDocument.setAuthorizationCode(paymentGatewayResponseDTO.getAuthorizationCode());
    }

    @Override
    public void validatePaymentStatusOrder(OrderDocument orderDocument) {
        if (!PaymentStatus.UNDEFINED.equals(orderDocument.getPaymentStatus())) {
            throw new BusinessException(PAYMENT_STATUS_MESSAGE.formatted(PaymentStatus.UNDEFINED.name()));
        }
    }

    @Override
    public void validatePayedPaymentAndUpdateOrder(OrderDocument orderDocument, PaymentGatewayResponseDTO paymentGateWayResponseDTO) {
        if (!STATUS_PAYED.equalsIgnoreCase(paymentGateWayResponseDTO.getStatus())) {
            throw new BusinessException(PAYMENT_STATUS_MESSAGE.formatted(STATUS_PAYED));
        }
        orderDocument.setPaymentStatus(PaymentStatus.PAYED);
        orderDocument.setStatus(OrderStatus.RECEIVED);
    }
}
