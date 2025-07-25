package com.fiap.techchallenge.adapter.out.external;

import com.fiap.techchallenge.adapter.out.external.dto.PaymentGatewayResponseDTO;
import com.fiap.techchallenge.application.port.out.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PaymentGatewayExternal implements PaymentGateway {

    private static final String URL_PAYMENT = "https://683f90f55b39a8039a54f47c.mockapi.io/mercadopago/v1/payment";
    private static final String URL_GENERATE = "https://683f90f55b39a8039a54f47c.mockapi.io/mercadopago/v1/generate";

    private final RestTemplate restTemplate;

    @Override
    public PaymentGatewayResponseDTO generatePayment(BigDecimal amount) {
        ResponseEntity<List<PaymentGatewayResponseDTO>> response = restTemplate.exchange(URL_GENERATE, HttpMethod.GET, null, new ParameterizedTypeReference<List<PaymentGatewayResponseDTO>>(){});
        return response.getBody().get(0);
    }

    @Override
    public PaymentGatewayResponseDTO confirmPayment(String paymentId) {
        ResponseEntity<List<PaymentGatewayResponseDTO>> response = restTemplate.exchange(URL_PAYMENT, HttpMethod.GET, null, new ParameterizedTypeReference<List<PaymentGatewayResponseDTO>>(){});
        return response.getBody().get(0);
    }
}
