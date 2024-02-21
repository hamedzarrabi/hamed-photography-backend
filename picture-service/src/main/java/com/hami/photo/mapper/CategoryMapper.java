package com.hami.photo.mapper;


import com.hami.photo.common.entity.category.Category;
import com.hami.photo.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    private static ModelMapper mapper;

    public static CategoryDto mapToDto(Category category) {
        return new CategoryDto(
                category.getCategoryId(),
                category.getDescription(),
                category.getDescription()
        );

//        return mapper.map(category, CategoryDto.class);
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getCategoryId(),
                categoryDto.getTitle(),
                categoryDto.getDescription()
        );
//        return mapper.map(categoryDto, Category.class);
    }
}
