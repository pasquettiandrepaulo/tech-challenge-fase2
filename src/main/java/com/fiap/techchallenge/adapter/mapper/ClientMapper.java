package com.fiap.techchallenge.adapter.mapper;

import com.fiap.techchallenge.adapter.in.controller.dto.request.client.ClientRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.client.ClientResponseDTO;
import com.fiap.techchallenge.domain.document.ClientDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO clientDocumentToClientResponseDTO(ClientDocument clientDocument);

    ClientDocument clientRequestDTOToClientDocument(ClientRequestDTO clientRequestDTO);
}
