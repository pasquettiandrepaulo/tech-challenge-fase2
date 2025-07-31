package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.product.ProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.product.ProductResponseDTO;
import com.fiap.techchallenge.adapter.mapper.ProductMapper;
import com.fiap.techchallenge.application.enumerate.ApplicationRuleMessage;
import com.fiap.techchallenge.application.exception.ApplicationException;
import com.fiap.techchallenge.application.port.in.ProductService;
import com.fiap.techchallenge.application.port.out.ProductRepository;
import com.fiap.techchallenge.domain.document.ProductDocument;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
        ProductDocument productDocument = ProductDocument.builder().build();
        productMapper.productRequestDTOToProductDocument(productRequestDTO, productDocument);
        ProductDocument productDocumentSaved = productRepository.save(productDocument);
        return productMapper.productDocumentToProductResponseDTO(productDocumentSaved);
    }

    @Override
    public List<ProductResponseDTO> filterByParams(ProductCategory category) {
        ProductDocument productDocumentQuery = ProductDocument.builder().category(category).build();
        Example<ProductDocument> exampleDocument = Example.of(productDocumentQuery);
        List<ProductDocument> products = productRepository.findAll(exampleDocument);
        return products.stream().map(productMapper::productDocumentToProductResponseDTO
        ).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO) {
        try{
            Optional<ProductDocument> productDocumentOptional = productRepository.findById(new ObjectId(id));
            if (productDocumentOptional.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.PRODUCT_NOT_FOUND_MESSAGE.getMessage(), id));
            }
            ProductDocument productDocument = productDocumentOptional.get();
            productMapper.productRequestDTOToProductDocument(productRequestDTO, productDocument);
            ProductDocument productDocumentSaved = productRepository.save(productDocument);
            return productMapper.productDocumentToProductResponseDTO(productDocumentSaved);
        } catch (Exception ex) {
            if (ex instanceof ApplicationException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<ProductDocument> productDocumentOptional = productRepository.findById(new ObjectId(id));
            if (productDocumentOptional.isEmpty()) {
                throw new ApplicationException(HttpStatus.NOT_FOUND.value(), String.format(ApplicationRuleMessage.PRODUCT_NOT_FOUND_MESSAGE.getMessage(), id));
            }
            productRepository.deleteById(new ObjectId(id));
        } catch (Exception ex) {
            if (ex instanceof ApplicationException) {
                throw ex;
            }
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }
}
