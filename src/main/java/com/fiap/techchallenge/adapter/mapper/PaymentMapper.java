package com.fiap.techchallenge.adapter.mapper;

import com.fiap.techchallenge.adapter.in.controller.dto.response.payment.PaymentResponseDTO;
import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponseDTO paymentGatewayDTOToPaymentResponseDTO(PaymentGatewayResponseDTO paymentGateWayResponseDTO);

}
