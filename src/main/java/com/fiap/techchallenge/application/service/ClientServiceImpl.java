package com.fiap.techchallenge.application.service;

import com.fiap.techchallenge.adapter.in.controller.dto.request.client.ClientRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.client.ClientResponseDTO;
import com.fiap.techchallenge.adapter.mapper.ClientMapper;
import com.fiap.techchallenge.application.port.in.ClientService;
import com.fiap.techchallenge.application.port.out.ClientRepository;
import com.fiap.techchallenge.domain.document.ClientDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;

    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {
        ClientDocument doc = clientMapper.clientRequestDTOToClientDocument(clientRequestDTO);
        return clientRepository.findOne(Example.of(doc))
                .map(clientMapper::clientDocumentToClientResponseDTO)
                .orElseGet(() -> {
                    ClientDocument saved = clientRepository.save(doc);
                    return clientMapper.clientDocumentToClientResponseDTO(saved);
                });
    }

    @Override
    public ClientResponseDTO findByCpf(String cpf) {
        ClientDocument clientDocument = clientRepository.findByCpf(cpf);
        return clientMapper.clientDocumentToClientResponseDTO(clientDocument);
    }
}
