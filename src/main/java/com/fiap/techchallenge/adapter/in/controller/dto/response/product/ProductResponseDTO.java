package com.fiap.techchallenge.adapter.in.controller.dto.response.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 370413811018168468L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(example = "665fb82184344b08993ed1dc")
    private ObjectId id;

    @Schema(example = "Hamburguer suprasumo")
    private String name;

    @Schema(example = "Queijo, Hamburguer, Cheader, Salada, Tomate, Bacon")
    private String description;

    private ProductCategory category;

    @Schema(example = "32.52")
    private BigDecimal price;

    @ArraySchema(schema = @Schema(type = "string", example = "https://site.com/img1.jpg"))
    private List<String> images;
}
