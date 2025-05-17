package com.smart.smartApi.mapper;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkCartItems(@MappingTarget Product product) {
        product.getCartItems().forEach(cartItem -> cartItem.setProduct(product));
    }

    @AfterMapping
    default void linkOrderItems(@MappingTarget Product product) {
        product.getOrderItems().forEach(orderItem -> orderItem.setProduct(product));
    }

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}