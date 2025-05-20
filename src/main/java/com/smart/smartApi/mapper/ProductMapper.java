package com.smart.smartApi.mapper;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "category.name", source = "category")
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkPromotion(@MappingTarget Product product) {
        product.getProductPromotions().forEach(productPromotion -> productPromotion.setProduct(product));
    }

    @Mapping(target = "category", source = "category.name")
    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category.name", source = "category")
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}