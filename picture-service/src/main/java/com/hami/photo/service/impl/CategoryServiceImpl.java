package com.hami.photo.service.impl;


import com.hami.photo.common.entity.category.Category;
import com.hami.photo.common.exception.ResourceNotFoundException;
import com.hami.photo.dto.CategoryDto;
import com.hami.photo.mapper.CategoryMapper;
import com.hami.photo.repository.CategoryRepository;
import com.hami.photo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.hami.photo.mapper.CategoryMapper.mapToDto;
import static com.hami.photo.mapper.CategoryMapper.mapToEntity;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
            Category category = mapToEntity(categoryDto);
            Category saveCategory = categoryRepository.save(category);
            return mapToDto(saveCategory);
        }


    @Override
    public CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category ", "id ", categoryId)
        );

        category.setCategoryId(categoryId);
        category.setTitle(category.getTitle());
        category.setDescription(category.getDescription());

        return mapToDto(category);
    }

    @Override
    public List<CategoryDto> finAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findCategoryById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category ", "id ", categoryId)
        );
        return mapToDto(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category ", "id ", categoryId)
        );
        categoryRepository.delete(category);
    }
}
