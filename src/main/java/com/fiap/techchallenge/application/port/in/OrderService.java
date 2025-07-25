package com.fiap.techchallenge.application.port.in;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderStatusRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.order.OrderResponseDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseDTO processOrder(OrderRequestDTO orderRequest);

    List<OrderResponseDTO> filterOrdersByParams(OrderStatus status);

    OrderResponseDTO changeOrderStatus(String id, OrderStatusRequestDTO orderStatusRequestDTO);
}
