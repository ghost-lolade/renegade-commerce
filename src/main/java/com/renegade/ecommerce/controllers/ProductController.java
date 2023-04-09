package com.renegade.ecommerce.controllers;

import com.renegade.ecommerce.common.ApiResponse;
import com.renegade.ecommerce.dto.ProductDTO;
import com.renegade.ecommerce.model.Category;
import com.renegade.ecommerce.service.CategoryService;
import com.renegade.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());

        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }

        Category category = optionalCategory.get();
        productService.addProduct(productDTO, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added successfully"), HttpStatus.CREATED);
    }
}
