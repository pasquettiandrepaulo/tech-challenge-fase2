package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderStatusRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.order.OrderResponseDTO;
import com.fiap.techchallenge.adapter.mapper.OrderMapper;
import com.fiap.techchallenge.application.enumerate.ApplicationRuleMessage;
import com.fiap.techchallenge.application.exception.ApplicationException;
import com.fiap.techchallenge.application.port.in.OrderService;
import com.fiap.techchallenge.application.port.out.OrderRepository;
import com.fiap.techchallenge.application.port.out.ProductRepository;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.document.ProductDocument;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import com.fiap.techchallenge.domain.exception.BusinessException;
import com.fiap.techchallenge.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
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

    private final OrderDomainService orderDomainService;

    @Override
    public OrderResponseDTO processOrder(OrderRequestDTO orderRequest) {
       List<ProductOrderDTO> products = orderRequest.getProducts().stream().map(this::getProductOrderDTO).toList();
       return orderMapper.orderDocumentToOrderResponseDTO(saveOrder(orderRequest, products));
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
        try {
            Optional<OrderDocument> orderDocument = orderRepository.findById(new ObjectId(id));
            if (orderDocument.isEmpty()){
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.ORDER_NOT_FOUND_ERROR.getMessage(), id));
            }
            orderDomainService.changeOrderStatus(orderDocument.get(), orderStatusRequestDTO.getStatus());
            OrderDocument savedOrder = orderRepository.save(orderDocument.get());
            return orderMapper.orderDocumentToOrderResponseDTO(savedOrder);
        } catch (Exception ex) {
            if (ex instanceof ApplicationException || ex instanceof BusinessException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    private ProductOrderDTO getProductOrderDTO(OrderProductRequestDTO orderProductRequestDTO) {
        try {
            Optional<ProductDocument> productDocumentOptional = productRepository.findById(new ObjectId(orderProductRequestDTO.getId()));
            if (productDocumentOptional.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.PRODUCT_NOT_FOUND_MESSAGE.getMessage(), orderProductRequestDTO.getId()));
            }
            ProductOrderDTO productOrderDTO = orderMapper.productDocumentToProductOrderDTO(productDocumentOptional.get());
            productOrderDTO.setQuantity(orderProductRequestDTO.getQuantity());
            productOrderDTO.setObservation(orderProductRequestDTO.getObservation());
            return productOrderDTO;
        } catch (Exception ex) {
            if (ex instanceof ApplicationException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    private OrderDocument saveOrder(OrderRequestDTO orderRequest, List<ProductOrderDTO> products) {
        OrderDocument orderDocument = orderDomainService.initializeOrder(orderRequest, products);
        return orderRepository.save(orderDocument);
    }
}
