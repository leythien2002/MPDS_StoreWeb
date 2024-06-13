package com.example.mpds.mapper;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component

public class CategoryMapper {

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setId(categoryEntity.getId());
        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }
}
