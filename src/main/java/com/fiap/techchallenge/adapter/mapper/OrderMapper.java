package com.fiap.techchallenge.adapter.mapper;

import com.fiap.techchallenge.adapter.in.controller.dto.response.order.OrderResponseDTO;
import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.document.ProductDocument;
import com.fiap.techchallenge.domain.dto.ProductOrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO orderDocumentToOrderResponseDTO(OrderDocument orderDocument);

    ProductOrderDTO productDocumentToProductOrderDTO(ProductDocument productDocument);
}
