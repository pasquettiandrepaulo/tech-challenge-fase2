package com.fiap.techchallenge.adapter.in.controller;

import com.fiap.techchallenge.adapter.in.controller.dto.request.client.ClientRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.request.product.ProductRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.client.ClientResponseDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.product.ProductResponseDTO;
import com.fiap.techchallenge.application.port.in.ClientService;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
@Tag(name = "Client", description = "API para gerenciamento de clientes")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @Operation(summary = "Criar cliente", description = "Realiza o registro do cliente passando dados no request")
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO productResponseDTO = clientService.save(clientRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("{cpf}")
    @Operation(summary = "Busca cliente por cpf", description = "Realiza a busca de cliente passando o cpf como parametro")
    public ResponseEntity<ClientResponseDTO> findByCpf(@PathVariable String cpf) {
        ClientResponseDTO client = clientService.findByCpf(cpf);
        return ResponseEntity.ok(client);
    }
}
