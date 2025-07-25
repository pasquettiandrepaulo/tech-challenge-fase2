package com.fiap.techchallenge.adapter.in.controller.dto.response.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6844704061271425824L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(example = "665fb82184344b08993ed1dc")
    private ObjectId id;

    @Schema(example = "KJFUIO")
    private String orderNumber;

    private OrderStatus status;

    private PaymentStatus paymentStatus;

    @Schema(example = "12356")
    private String paymentId;

    private List<ProductOrderDTO> products;

    @Schema(example = "665fb82184344b08993ed1dc")
    private String clientId;

    @Schema(example = "123.52")
    private BigDecimal amount;

}
