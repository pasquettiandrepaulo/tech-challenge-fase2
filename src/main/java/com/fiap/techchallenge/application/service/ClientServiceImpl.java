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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;

    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {
        ClientDocument clientDocumentFilter = clientMapper.clientRequestDTOToClientDocument(clientRequestDTO);
        Example<ClientDocument> clientDocumentExample = Example.of(clientDocumentFilter);
        Optional<ClientDocument> clientDocumentOptionalFilter = clientRepository.findOne(clientDocumentExample);
        if (clientDocumentOptionalFilter.isPresent()) {
            return clientMapper.clientDocumentToClientResponseDTO(clientDocumentOptionalFilter.get());
        }
        ClientDocument clientDocument = clientMapper.clientRequestDTOToClientDocument(clientRequestDTO);
        ClientDocument clientDocumentSaved = clientRepository.save(clientDocument);
        return clientMapper.clientDocumentToClientResponseDTO(clientDocumentSaved);
    }

    @Override
    public ClientResponseDTO findByCpf(String cpf) {
        ClientDocument clientDocument = clientRepository.findByCpf(cpf);
        return clientMapper.clientDocumentToClientResponseDTO(clientDocument);
    }
}
