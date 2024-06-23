package com.example.mpds.mapper;

import com.example.mpds.dto.StrapDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.entity.StrapEntity;
import com.example.mpds.entity.TypeEntity;
import org.springframework.stereotype.Component;

@Component

public class TypeMapper {
    public TypeDTO toDTO(TypeEntity typeEntity) {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setName(typeEntity.getName());
        typeDTO.setId(typeEntity.getId());
        return typeDTO;
    }
    public TypeEntity toEntity(TypeDTO typeDTO){
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(typeDTO.getName());
        return typeEntity;
    }
}
