package com.smart.smartApi.controller;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.mapper.ProductMapper;
import com.smart.smartApi.model.Product;
import com.smart.smartApi.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductController(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }


    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search/{name}")
    public List<ProductDto> searchByName(@PathVariable String name) {
        List<Product> productList = productRepository.findByName(name);
        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }


    @PostMapping
    public ProductDto saveProduct(@RequestBody @NonNull @Valid ProductDto productDto) {
        Product productEntity = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody @NonNull ProductDto productDto) {
        if (productDto.getId() == null)
            throw new IllegalArgumentException("Product ID is missing. Use /new to create a product");
        Product productEntity = productRepository.findById(productDto.getId()).orElseThrow(EntityNotFoundException::new);

        productEntity = productMapper.partialUpdate(productDto, productEntity);
        return productMapper.toDto(productRepository.save(productEntity));
    }
}
