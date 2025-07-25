package com.fiap.techchallenge.adapter.in.controller;

import com.fiap.techchallenge.adapter.in.controller.dto.request.product.ProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.product.ProductResponseDTO;
import com.fiap.techchallenge.application.port.in.ProductService;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API para gerenciamento de produtos")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Criar produto", description = "Criação de produto passando os dados para o cadastro")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.save(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Listagem de produtos", description = "Listagem dos produtos podendo filtrar por categoria")
    public ResponseEntity<List<ProductResponseDTO>> listByParams(@RequestParam ProductCategory category) {
        List<ProductResponseDTO> products = productService.filterByParams(category);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização do produto", description = "Atualização do produto passando o request e o id do produto para a atualização")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.update(id, productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remoção do produto", description = "Remoção do produto passando o id do produto")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
