package com.smart.smartApi.controller;

import com.smart.smartApi.dto.ProductDto;
import com.smart.smartApi.dto.ProductPageResponse;
import com.smart.smartApi.exception.EmptyFileException;
import com.smart.smartApi.service.ProductService;
import com.smart.smartApi.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }


    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search/{name}")
    public List<ProductDto> searchByName(@PathVariable String name) {
        return productService.searchByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestPart @NonNull @Valid ProductDto productDto, @RequestPart MultipartFile file) throws IOException, EmptyFileException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send another file!");
        }

        return new ResponseEntity<>(productService.addProduct(productDto, file), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestPart @NonNull ProductDto productDto, @RequestPart(required = false) MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) file = null;

        return ResponseEntity.ok(productService.updateProduct(productDto, file));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws IOException {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/allProductsPage")
    public ResponseEntity<ProductPageResponse> getAllProductsWithPagination(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
        return ResponseEntity.ok(productService.getAllProductsWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/allProductsPageAndSorting")
    public ResponseEntity<ProductPageResponse> getAllProductsWithPaginationAndSorting(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String sortDirection) {
        return ResponseEntity.ok(productService.getAllProductsWithPaginationAndSorting(pageNumber, pageSize, sortBy, sortDirection));
    }
}
