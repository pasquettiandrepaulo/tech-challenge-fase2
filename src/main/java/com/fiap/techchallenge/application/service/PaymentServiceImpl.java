package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.response.payment.PaymentResponseDTO;
import com.fiap.techchallenge.adapter.mapper.PaymentMapper;
import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import com.fiap.techchallenge.application.enumerate.ApplicationRuleMessage;
import com.fiap.techchallenge.application.exception.ApplicationException;
import com.fiap.techchallenge.application.port.in.PaymentService;
import com.fiap.techchallenge.application.port.out.OrderRepository;
import com.fiap.techchallenge.application.port.out.PaymentGateway;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.exception.BusinessException;
import com.fiap.techchallenge.domain.service.PaymentDomainService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;

    private final PaymentMapper paymentMapper;

    private final PaymentGateway paymentGateway;

    private final PaymentDomainService paymentDomainService;

    @Override
    public PaymentResponseDTO getPayment(String orderId) {
        try{
            Optional<OrderDocument> orderDocumentOptional = orderRepository.findById(new ObjectId(orderId));

            if (orderDocumentOptional.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.ORDER_NOT_FOUND_ERROR.getMessage(), orderId));
            }

            PaymentGatewayResponseDTO paymentGateWayResponseDTO = paymentGateway.generatePayment(orderDocumentOptional.get().getAmount());
            paymentDomainService.validateCreatedPaymentAndUpdateOrder(orderDocumentOptional.get(), paymentGateWayResponseDTO);
            orderRepository.save(orderDocumentOptional.get());

            return paymentMapper.paymentGatewayDTOToPaymentResponseDTO(paymentGateWayResponseDTO);
        } catch (Exception ex) {
            if (ex instanceof ApplicationException || ex instanceof BusinessException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @Override
    public PaymentResponseDTO payCheckout(String orderId) {
        try{
            Optional<OrderDocument> orderDocumentOptional = orderRepository.findById(new ObjectId(orderId));

            if (orderDocumentOptional.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.ORDER_NOT_FOUND_ERROR.getMessage(), orderId));
            }

            paymentDomainService.validatePaymentStatusOrder(orderDocumentOptional.get());

            PaymentGatewayResponseDTO paymentGateWayResponseDTO = paymentGateway.confirmPayment(orderDocumentOptional.get().getPaymentId());
            paymentDomainService.validatePayedPaymentAndUpdateOrder(orderDocumentOptional.get(), paymentGateWayResponseDTO);
            orderRepository.save(orderDocumentOptional.get());

            return paymentMapper.paymentGatewayDTOToPaymentResponseDTO(paymentGateWayResponseDTO);
        } catch (Exception ex) {
            if (ex instanceof ApplicationException || ex instanceof BusinessException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }
}
