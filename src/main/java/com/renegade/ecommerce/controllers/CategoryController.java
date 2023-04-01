package com.renegade.ecommerce.controllers;

import com.renegade.ecommerce.common.ApiResponse;
import com.renegade.ecommerce.model.Category;
import com.renegade.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<> (body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Created the category"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId, @Valid @RequestBody Category category) {
//        Check to see if th e category exists
        if (Objects.nonNull(categoryService.readCategory(categoryId))) {
//            If the category exists, then update it.
            categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Updated the category"), HttpStatus.OK);
        }

//        If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
    }
}
