package com.fiap.techchallenge.domain.document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Document(value = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = -6391575300221196150L;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String orderNumber;

    private OrderStatus status;

    private PaymentStatus paymentStatus;

    private String paymentId;

    private String authorizationCode;

    private String clientId;

    private BigDecimal amount;

    private List<ProductOrderDTO> products;
}