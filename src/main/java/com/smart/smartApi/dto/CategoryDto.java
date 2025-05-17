package com.smart.smartApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.smart.smartApi.model.Category}
 */
@Data
public class CategoryDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String name;
}