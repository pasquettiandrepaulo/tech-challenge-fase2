package com.fiap.techchallenge.domain.document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Document(value = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = -5966420702239528336L;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;

    private String email;

    private String cpf;
}
