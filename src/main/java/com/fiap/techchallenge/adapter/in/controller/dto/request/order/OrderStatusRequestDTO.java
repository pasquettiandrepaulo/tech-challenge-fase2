package com.fiap.techchallenge.adapter.in.controller.dto.request.order;

import com.fiap.techchallenge.domain.enumerate.OrderStatus;
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
public class OrderStatusRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4775630513972312193L;

    private OrderStatus status;
}
