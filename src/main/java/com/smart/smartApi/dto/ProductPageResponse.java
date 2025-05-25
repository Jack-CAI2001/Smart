package com.smart.smartApi.dto;

import java.util.List;

public record ProductPageResponse(List<ProductDto> productDtoList,
                                  Integer pageNumber,
                                  Integer pageSize,
                                  long totalElements,
                                  int totalPages,
                                  boolean isLast) {
}