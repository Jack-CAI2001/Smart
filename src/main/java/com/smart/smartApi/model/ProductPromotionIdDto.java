package com.smart.smartApi.model;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductPromotionId}
 */
@Value
public class ProductPromotionIdDto implements Serializable {
    @NotNull
    Integer productId;
    @NotNull
    Integer promotionId;
}