package com.example.mpds.services.impl;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.mapper.CategoryMapper;
import com.example.mpds.repository.CategoryRepository;
import com.example.mpds.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();

        List<CategoryEntity> lstCategoryEntities = categoryRepository.findAll();
        for (CategoryEntity entity : lstCategoryEntities) {
            listCategoryDTO.add(categoryMapper.toDTO(entity));
        }


        return listCategoryDTO;
    }
}
