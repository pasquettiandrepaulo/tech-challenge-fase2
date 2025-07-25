package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderStatusRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.order.OrderResponseDTO;
import com.fiap.techchallenge.adapter.mapper.OrderMapper;
import com.fiap.techchallenge.application.port.in.OrderService;
import com.fiap.techchallenge.application.port.out.OrderRepository;
import com.fiap.techchallenge.application.port.out.ProductRepository;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.document.ProductDocument;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public OrderResponseDTO processOrder(OrderRequestDTO orderRequest) {
       List<ProductOrderDTO> products = orderRequest.getProducts().stream().map(this::getProductOrderDTO).toList();
       double totalValue = getTotalValue(products);
       return orderMapper.orderDocumentToOrderResponseDTO(saveOrder(orderRequest, totalValue, products));
    }

    @Override
    public List<OrderResponseDTO> filterOrdersByParams(OrderStatus status) {
        if (Objects.nonNull(status)) {
            return orderRepository.findByStatus(status).stream().map(orderMapper::orderDocumentToOrderResponseDTO).toList();
        }
        return orderRepository.findAll().stream().map(orderMapper::orderDocumentToOrderResponseDTO).toList();
    }

    @Override
    public OrderResponseDTO changeOrderStatus(String id, OrderStatusRequestDTO orderStatusRequestDTO) {
        Optional<OrderDocument> orderDocument = orderRepository.findById(new ObjectId(id));
        if (orderDocument.isEmpty()){
            return null;
        }
        orderDocument.get().setStatus(orderStatusRequestDTO.getStatus());
        OrderDocument savedOrder = orderRepository.save(orderDocument.get());
        return orderMapper.orderDocumentToOrderResponseDTO(savedOrder);
    }

    private static double getTotalValue(List<ProductOrderDTO> products) {
        return products.stream().mapToDouble(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())).doubleValue()).sum();
    }

    private ProductOrderDTO getProductOrderDTO(OrderProductRequestDTO orderProductRequestDTO) {
        Optional<ProductDocument> productDocumentOptional = productRepository.findById(new ObjectId(orderProductRequestDTO.getId()));
        if (productDocumentOptional.isEmpty()) {
            return null;
        }
        ProductOrderDTO productOrderDTO = orderMapper.productDocumentToProductOrderDTO(productDocumentOptional.get());
        productOrderDTO.setQuantity(orderProductRequestDTO.getQuantity());
        productOrderDTO.setObservation(orderProductRequestDTO.getObservation());
        return productOrderDTO;
    }

    private OrderDocument saveOrder(OrderRequestDTO orderRequest, double totalValue, List<ProductOrderDTO> products) {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setStatus(OrderStatus.WAITING_PAYMENT);
        orderDocument.setPaymentStatus(PaymentStatus.UNDEFINED);
        orderDocument.setClientId(orderRequest.getClientId());
        orderDocument.setOrderNumber(RandomStringUtils.randomAlphanumeric(5).toUpperCase());
        orderDocument.setAmount(BigDecimal.valueOf(totalValue));
        orderDocument.setProducts(products);
        return orderRepository.save(orderDocument);
    }
}
