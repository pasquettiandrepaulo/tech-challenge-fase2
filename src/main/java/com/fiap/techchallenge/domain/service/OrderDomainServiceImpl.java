package com.fiap.techchallenge.domain.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.order.OrderRequestDTO;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import com.fiap.techchallenge.domain.enumerate.PaymentStatus;
import com.fiap.techchallenge.domain.exception.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    private static final String STATUS_MESSAGE = "Status invalid must be [%s] ";
    private static final String STATUS_NOT_PRESENT = "No present status";

    @Override
    public void changeOrderStatus(OrderDocument orderDocument, OrderStatus status) {
        OrderStatus actualStatus = orderDocument.getStatus();
        validateStatusChangeOrder(status, actualStatus);
        orderDocument.setStatus(status);
    }

    @Override
    public OrderDocument initializeOrder(OrderRequestDTO orderRequest, List<ProductOrderDTO> products) {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setStatus(OrderStatus.WAITING_PAYMENT);
        orderDocument.setPaymentStatus(PaymentStatus.UNDEFINED);
        orderDocument.setClientId(orderRequest.getClientId());
        orderDocument.setOrderNumber(generateOrderNumber());
        orderDocument.setAmount(BigDecimal.valueOf(getTotalOrderValue(products)));
        orderDocument.setProducts(products);
        return orderDocument;
    }

    private static String generateOrderNumber() {
        return RandomStringUtils.randomAlphanumeric(5).toUpperCase();
    }

    private double getTotalOrderValue(List<ProductOrderDTO> products) {
        return products.stream().mapToDouble(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())).doubleValue()).sum();
    }

    private static void validateStatusChangeOrder(OrderStatus status, OrderStatus actualStatus) {
        if (Objects.nonNull(status)){
            if(OrderStatus.WAITING_PAYMENT.equals(actualStatus) && !OrderStatus.RECEIVED.equals(status))
                throw new BusinessException(String.format(STATUS_MESSAGE, OrderStatus.RECEIVED.name()));

            if (OrderStatus.RECEIVED.equals(actualStatus) && !OrderStatus.IN_PREPARATION.equals(status))
                throw new BusinessException(String.format(STATUS_MESSAGE, OrderStatus.IN_PREPARATION.name()));

            if (OrderStatus.IN_PREPARATION.equals(actualStatus) && !OrderStatus.READY.equals(status))
                throw new BusinessException(String.format(STATUS_MESSAGE, OrderStatus.READY.name()));

            if (OrderStatus.READY.equals(actualStatus) && !OrderStatus.FINISHED.equals(status))
                throw new BusinessException(String.format(STATUS_MESSAGE, OrderStatus.FINISHED.name()));

        } else {
            throw new BusinessException(STATUS_NOT_PRESENT);
        }
    }
}
