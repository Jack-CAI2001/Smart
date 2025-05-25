package com.smart.smartApi.mapper;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkPromotion(@MappingTarget Product product) {
        product.getProductPromotions().forEach(productPromotion -> productPromotion.setProduct(product));
    }

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}