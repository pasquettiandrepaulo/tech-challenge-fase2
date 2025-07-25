package com.fiap.techchallenge.adapter.in.controller.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1331199879865147446L;

    @Schema(example = "665fb82184344b08993ed1dc")
    private String clientId;

    private List<OrderProductRequestDTO> products;

}
