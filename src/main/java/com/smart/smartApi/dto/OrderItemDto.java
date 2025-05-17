package com.smart.smartApi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.smart.smartApi.model.OrderItem}
 */
@Data
public class OrderItemDto implements Serializable {
    Integer id;
    @NotNull
    Integer quantity;
    @NotNull
    BigDecimal unitPrice;
}