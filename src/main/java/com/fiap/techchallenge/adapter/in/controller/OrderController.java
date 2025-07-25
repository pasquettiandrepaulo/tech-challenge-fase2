package com.fiap.techchallenge.adapter.in.controller;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderStatusRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.order.OrderResponseDTO;
import com.fiap.techchallenge.application.port.in.OrderService;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Tag(name = "Order", description = "API para gerenciamento de ordens")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Buscar orders", description = "Buscar ordens passando status por parametro")
    ResponseEntity<List<OrderResponseDTO>> getOrdersByParams(@RequestParam(required = false) OrderStatus status){
        return ResponseEntity.ok(orderService.filterOrdersByParams(status));
    }

    @PostMapping
    @Operation(summary = "Criação de ordem", description = "Criação de ordem passando dados para registro por parametro")
    ResponseEntity<OrderResponseDTO> processOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return ResponseEntity.ok(orderService.processOrder(orderRequestDTO));
    }

    @PutMapping("{id}")
    @Operation(summary = "Alterar status de order", description = " Alterar o status  da ordem passando status por parametro")
    ResponseEntity<OrderResponseDTO> changeOrderStatus(@PathVariable String id, @RequestBody OrderStatusRequestDTO orderStatusRequestDTO){
        return ResponseEntity.ok(orderService.changeOrderStatus(id, orderStatusRequestDTO));
    }

}
