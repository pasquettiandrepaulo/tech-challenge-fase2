package com.fiap.techchallenge.adapter.in.controller.dto.request.product;

import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1791417384883049355L;

    @Schema(example = "Hamburguer suprasumo")
    private String name;

    @Schema(example = "Queijo, Hamburguer, Cheader, Salada, Tomate, Bacon")
    private String description;

    private ProductCategory category;

    @Schema(example = "35.60")
    private BigDecimal price;

    @ArraySchema(schema = @Schema(type = "string", example = "https://site.com/img1.jpg"))
    private List<String> images;
}
