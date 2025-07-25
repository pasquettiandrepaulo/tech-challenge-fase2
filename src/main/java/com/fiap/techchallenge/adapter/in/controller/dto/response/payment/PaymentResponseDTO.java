package com.fiap.techchallenge.adapter.in.controller.dto.response.payment;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class PaymentResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7086416389895231861L;

    @Schema(example = "PAYED")
    private String status;

    @Schema(example = "Pagamento realizado com sucesso")
    private String message;

    @Schema(example = "ATH-02354")
    private String authorizationCode;

    @Schema(example = "QR_CODE")
    private String paymentType;

    @Schema(example = "125487")
    private String paymentId;

    @Schema(example = "s564gsfgsd56fg4fsdgs65d4fgsdf48g9f4g9s4t89we7hfg654fgf6sdf54.5fs5f4s6f4s")
    private String qrCode;

}
