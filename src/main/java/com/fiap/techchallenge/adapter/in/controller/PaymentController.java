package com.fiap.techchallenge.adapter.in.controller;

import com.fiap.techchallenge.adapter.in.controller.dto.response.payment.PaymentResponseDTO;
import com.fiap.techchallenge.application.port.in.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "API para gerenciamento de pagamentos")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("{orderId}")
    @Operation(summary = "Busca dados de checkout da ordem", description = "Busca dados de checkout da ordem pelo id")
    ResponseEntity<PaymentResponseDTO> getPaymentCheckout(@PathVariable String orderId){
        return ResponseEntity.ok(paymentService.getPayment(orderId));
    }

    @PostMapping("{orderId}")
    @Operation(summary = "Confirma o pagamento da ordem", description = "Realizada a confirmação do pagamento da ordem passando o id")
    ResponseEntity<PaymentResponseDTO> payPaymentCheckout(@PathVariable String orderId){
        return ResponseEntity.ok(paymentService.payCheckout(orderId));
    }
}
