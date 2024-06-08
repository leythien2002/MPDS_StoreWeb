package com.example.mpds.services;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public List<CategoryDTO> getAll();
}
