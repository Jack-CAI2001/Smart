package com.smart.smartApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link com.smart.smartApi.model.Product}
 */
@Data
public class ProductDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String name;
    String description;
    @NotNull
    BigDecimal price;
    Instant createdAt;
    String imageUrl;
    String category;
    Set<ProductPromotionDto> productPromotions;
}