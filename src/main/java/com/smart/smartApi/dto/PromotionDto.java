package com.smart.smartApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.smart.smartApi.model.Promotion}
 */
@Data
public class PromotionDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String title;
    String description;
    @NotNull
    LocalDate startDate;
    @NotNull
    LocalDate endDate;
    @NotNull
    @Size(max = 20)
    String discountType;
    @NotNull
    BigDecimal discountValue;
}