package com.fiap.techchallenge.adapter.in.controller.dto.request.order;

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
public class OrderProductRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4964297913739936430L;

    @Schema(example = "665fb82184344b08993ed1dc")
    private String id;

    @Schema(example = "10")
    private Integer quantity;

    @Schema(example = "Sem queijo")
    private String observation;
}
