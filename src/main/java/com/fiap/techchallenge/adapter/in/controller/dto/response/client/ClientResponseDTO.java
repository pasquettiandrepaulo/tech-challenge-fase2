package com.fiap.techchallenge.adapter.in.controller.dto.response.client;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serial;
import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6520348551616034029L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(example = "665fb82184344b08993ed1dc")
    private ObjectId id;

    @Schema(example = "Gustavo Frinch")
    private String name;

    @Schema(example = "gustavo.frinch@pojoshermanos.com")
    private String email;

    @Schema(example = "00000000000")
    private String cpf;
}
