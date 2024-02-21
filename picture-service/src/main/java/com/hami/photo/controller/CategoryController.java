package com.hami.photo.controller;

import com.hami.photo.dto.CategoryDto;
import com.hami.photo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RequestMapping("/api/v1/category")
@RestController
@Tag(name = "Category", description = "category hamed photo.")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired private CategoryService categoryService;

    @Operation(
            summary = "create category",
            description = "create new category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful create category")
    })
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {

        LOGGER.info("Category Id: " + categoryDto.getCategoryId() + " Title: " + categoryDto.getTitle() + " Description: " +categoryDto.getDescription());

        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
    }

    @PutMapping("/update/{category_id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("category_id")UUID categoryId, @RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto category = categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDto>> findAllCategory() {
        List<CategoryDto> finAllCategory = categoryService.finAllCategory();
        return new ResponseEntity<>(finAllCategory, HttpStatus.OK);
    }
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("category_id") UUID categoryId) {
        CategoryDto categoryById = categoryService.findCategoryById(categoryId);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("category_id") UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Delete category by id : " + categoryId + " is Successfully.", HttpStatus.OK);
    }
}
