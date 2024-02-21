package com.hami.photo.service;




import com.hami.photo.dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto);
    List<CategoryDto> finAllCategory();
    CategoryDto findCategoryById(UUID categoryId);
    void deleteCategory(UUID categoryId);
}
