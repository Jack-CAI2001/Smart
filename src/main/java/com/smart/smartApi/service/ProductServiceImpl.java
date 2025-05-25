package com.smart.smartApi.service;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.exception.ProductNotFoundException;
import com.smart.smartApi.exception.StorageFileExistsException;
import com.smart.smartApi.mapper.ProductMapper;
import com.smart.smartApi.model.Product;
import com.smart.smartApi.repositories.ProductRepository;
import com.smart.smartApi.utils.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final StorageService storageService;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, StorageService storageService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id = " + id));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByName(String name) {
        List<Product> productList = productRepository.findByName(name);
        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto addProduct(ProductDto productDto, MultipartFile file) {
        if (Files.exists(storageService.load(file.getOriginalFilename()))) {
            throw new StorageFileExistsException("File already exists! Please enter another file name!");
        }
        // gerez le cas ou productRepository.save fail et que le fichier est quand même sauvegarder
        storageService.store(file);

        productDto.setImageUrl(FileUtils.buildFileAccessUrl(storageService.load(file.getOriginalFilename())));

        Product productEntity = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, MultipartFile file) throws IOException {
        if (productDto.getId() == null)
            throw new IllegalArgumentException("Product ID is missing. Use /new to create a product");

        Product productEntity = productRepository.findById(productDto.getId()).orElseThrow(EntityNotFoundException::new);
        if (file != null && !file.isEmpty()) {
            String oldImagePath = productEntity.getImageUrl();
            if (oldImagePath != null) {
                Path oldPath = Paths.get(oldImagePath);
                Files.deleteIfExists(oldPath);
            }

            if (Files.exists(storageService.load(file.getOriginalFilename()))) {
                throw new StorageFileExistsException("File already exists! Please enter another file name!");
            }

            storageService.store(file);
            productDto.setImageUrl(storageService.load(file.getOriginalFilename()).toString());
        }

        productEntity = productMapper.partialUpdate(productDto, productEntity);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    @Override
    public void deleteProductById(Integer id) throws IOException {
        ProductDto product = getProductById(id);
        if (product.getImageUrl() != null) {
            Path path = Paths.get(product.getImageUrl());
            Files.deleteIfExists(path);
        }
        productRepository.deleteById(product.getId());
    }
}
