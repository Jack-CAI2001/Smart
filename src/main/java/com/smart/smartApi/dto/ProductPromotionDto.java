package com.smart.smartApi.dto;

import com.smart.smartApi.model.ProductPromotionIdDto;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.smart.smartApi.model.ProductPromotion}
 */
@Data
public class ProductPromotionDto implements Serializable {
    ProductPromotionIdDto id;
    PromotionDto promotion;
}