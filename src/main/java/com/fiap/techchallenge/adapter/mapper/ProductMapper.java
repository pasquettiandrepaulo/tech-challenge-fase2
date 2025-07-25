package com.fiap.techchallenge.adapter.mapper;

import com.fiap.techchallenge.adapter.in.controller.dto.request.product.ProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.product.ProductResponseDTO;
import com.fiap.techchallenge.domain.document.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    void productRequestDTOToProductDocument(ProductRequestDTO productRequestDTO, @MappingTarget ProductDocument productDocument);

    ProductResponseDTO productDocumentToProductResponseDTO(ProductDocument productDocument);

}
