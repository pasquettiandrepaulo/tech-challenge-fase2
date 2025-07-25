package com.fiap.techchallenge.application.port.in;

import com.fiap.techchallenge.adapter.in.controller.dto.request.product.ProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.product.ProductResponseDTO;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDTO save(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> filterByParams(ProductCategory category);

    ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO);

    void delete(String id);

}
