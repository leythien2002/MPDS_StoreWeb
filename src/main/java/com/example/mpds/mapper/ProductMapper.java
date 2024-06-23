package com.example.mpds.mapper;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity toEntity(ProductDTO dto){
        ProductEntity entity=new ProductEntity();
//        CategoryEntity category=new CategoryEntity(); cai nay nen lay tu service ve roi dua vao entity sau
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        entity.setImage3(dto.getImage3());
        entity.setPrice(dto.getPrice());
        entity.setGender(dto.getGender());
//        entity.setType(dto.getType());
//        entity.setDialSize(dto.getDialSize());
        //nen set khong hay de no default ??
        entity.setStatus(dto.getStatus());
        return entity;

    }
    public ProductDTO toDTO(ProductEntity entity){
        ProductDTO dto=new ProductDTO();
        dto.setId(entity.getId());//set id for add product to cart
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setName(entity.getName());
        dto.setImage1(entity.getImage1());
        dto.setImage2(entity.getImage2());
        dto.setImage3(entity.getImage3());
        dto.setCategoryName(entity.getCategory().getName());
        dto.setType(entity.getType().getName());
        dto.setDialSize(entity.getDialSize().getName());
        dto.setStrap(entity.getStrap().getName());

        dto.setStatus(entity.getStatus());
        dto.setGender(entity.getGender());
        return dto;
    }
}
