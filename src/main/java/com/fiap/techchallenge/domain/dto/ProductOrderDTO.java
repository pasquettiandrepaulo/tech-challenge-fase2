package com.fiap.techchallenge.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8217695483928953349L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(example = "665fb82184344b08993ed1dc")
    private ObjectId id;

    @Schema(example = "Hamburguer A")
    private String name;

    @Schema(example = "Queijo, Hamburguer, Cheader, Salada, Tomate, Bacon")
    private String description;

    @Schema(example = "Sem Queijo")
    private String observation;

    private ProductCategory category;

    @Schema(example = "10")
    private Integer quantity;

    @Schema(example = "123.54")
    private BigDecimal price;

    @ArraySchema(schema = @Schema(type = "string", example = "https://site.com/img1.jpg"))
    private List<String> images;

}
