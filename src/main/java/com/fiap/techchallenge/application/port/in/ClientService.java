package com.fiap.techchallenge.application.port.in;

import com.fiap.techchallenge.adapter.in.controller.dto.request.client.ClientRequestDTO;
import com.fiap.techchallenge.adapter.in.controller.dto.response.client.ClientResponseDTO;

public interface ClientService {

    ClientResponseDTO save(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO findByCpf(String cpf);

}
