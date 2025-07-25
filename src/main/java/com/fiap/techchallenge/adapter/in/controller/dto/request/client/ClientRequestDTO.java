package com.fiap.techchallenge.adapter.in.controller.dto.request.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -173043117367565062L;

    @Schema(example = "Gustavo Frinch")
    private String name;

    @Schema(example = "gustavo.frinch@pojoshermanos.com")
    private String email;

    @Schema(example = "00000000000")
    private String cpf;
}
