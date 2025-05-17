package com.smart.smartApi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.smart.smartApi.model.CartItem}
 */
@Data
public class CartItemDto implements Serializable {
    Integer id;
    @NotNull
    Integer quantity;
}