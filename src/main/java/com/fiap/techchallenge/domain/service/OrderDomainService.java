package com.fiap.techchallenge.domain.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;

import java.util.List;

public interface OrderDomainService {

    void changeOrderStatus(OrderDocument orderDocument, OrderStatus status);

    OrderDocument initializeOrder(OrderRequestDTO orderRequest, List<ProductOrderDTO> products);
}
