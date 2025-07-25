package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.response.payment.PaymentResponseDTO;
import com.fiap.techchallenge.adapter.mapper.PaymentMapper;
import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import com.fiap.techchallenge.application.port.in.PaymentService;
import com.fiap.techchallenge.application.port.out.OrderRepository;
import com.fiap.techchallenge.application.port.out.PaymentGateway;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final String STATUS_PAYED = "PAYED";
    private static final String STATUS_CREATED = "CREATED";

    private final OrderRepository orderRepository;

    private final PaymentMapper paymentMapper;

    private final PaymentGateway paymentGateway;

    @Override
    public PaymentResponseDTO getPayment(String orderId) {
        Optional<OrderDocument> orderDocumentOptional = orderRepository.findById(new ObjectId(orderId));

        if (orderDocumentOptional.isEmpty()) {
            return null;
        }

        PaymentGatewayResponseDTO paymentGateWayResponseDTO = paymentGateway.generatePayment(orderDocumentOptional.get().getAmount());

        if (!STATUS_CREATED.equalsIgnoreCase(paymentGateWayResponseDTO.getStatus())) {
            return null;
        }

        orderDocumentOptional.get().setPaymentId(paymentGateWayResponseDTO.getPaymentId());
        orderDocumentOptional.get().setAuthorizationCode(paymentGateWayResponseDTO.getAuthorizationCode());
        orderRepository.save(orderDocumentOptional.get());

        return paymentMapper.paymentGatewayDTOToPaymentResponseDTO(paymentGateWayResponseDTO);
    }

    @Override
    public PaymentResponseDTO payCheckout(String orderId) {
        Optional<OrderDocument> orderDocumentOptional = orderRepository.findById(new ObjectId(orderId));

        if (orderDocumentOptional.isEmpty()) {
            return null;
        }

        if (!PaymentStatus.UNDEFINED.equals(orderDocumentOptional.get().getPaymentStatus())) {
            return null;
        }

        PaymentGatewayResponseDTO paymentGateWayResponseDTO = paymentGateway.confirmPayment(orderDocumentOptional.get().getPaymentId());

        if (!STATUS_PAYED.equalsIgnoreCase(paymentGateWayResponseDTO.getStatus())) {
            return null;
        }

        orderDocumentOptional.get().setPaymentStatus(PaymentStatus.PAYED);
        orderDocumentOptional.get().setStatus(OrderStatus.RECEIVED);
        orderRepository.save(orderDocumentOptional.get());

        return paymentMapper.paymentGatewayDTOToPaymentResponseDTO(paymentGateWayResponseDTO);
    }
}
