package com.smart.smartApi.service;

import com.smart.smartApi.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto getProductById(Integer id);

    List<ProductDto> getAllProducts();

    List<ProductDto> searchByName(String name);

    ProductDto addProduct(ProductDto productDto, MultipartFile file);

    ProductDto updateProduct(ProductDto productDto, MultipartFile file) throws IOException;

    void deleteProductById(Integer id) throws IOException;

}
