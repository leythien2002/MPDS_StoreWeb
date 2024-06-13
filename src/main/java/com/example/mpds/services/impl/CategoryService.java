package com.example.mpds.services.impl;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.mapper.CategoryMapper;
import com.example.mpds.repository.CategoryRepository;
import com.example.mpds.repository.InvoiceRepository;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();

        List<CategoryEntity> lstCategoryEntities = categoryRepository.findAll();
        for (CategoryEntity entity : lstCategoryEntities) {
            listCategoryDTO.add(categoryMapper.toDTO(entity));
        }


        return listCategoryDTO;
    }

    public String deleteById(Long id){
        List<ProductEntity>lstProd =productRepository.findByCategory_Id((id.intValue()));
        if(lstProd.isEmpty()){
            categoryRepository.deleteById(id);
            return "Delete Suscessfully";
        }

        else {List<Integer> productIds = lstProd.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
            return  "Delete Failed, Products associated with category ID " + id + ": " + productIds;
//            System.out.println("Products associated with category ID " + id + ": " + productIds);
        }
    }
    public  void insertCategory(CategoryDTO categoryDTO){
        CategoryEntity newCategory = categoryMapper.toEntity(categoryDTO);

        categoryRepository.save(newCategory);
    }
    public void updateCategory(CategoryDTO categoryDTO)
    {
        CategoryEntity existingCategory = categoryRepository.findById((long) categoryDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
    }

}
