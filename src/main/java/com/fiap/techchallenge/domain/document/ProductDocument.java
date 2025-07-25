package com.fiap.techchallenge.domain.document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fiap.techchallenge.domain.enumerate.ProductCategory;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Document(value = "product")
@AllArgsConstructor
@Getter
@Setter
public class ProductDocument implements Serializable {

    @Serial
    private static final long serialVersionUID = -2003635648376997400L;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;

    private String description;

    private ProductCategory category;

    private BigDecimal price;

    private List<String> images;
}
