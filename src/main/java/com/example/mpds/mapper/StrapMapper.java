package com.example.mpds.mapper;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.StrapDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.StrapEntity;
import org.springframework.stereotype.Component;

@Component

public class StrapMapper {

    public StrapDTO toDTO(StrapEntity strapEntity) {
        StrapDTO strapDTO = new StrapDTO();
        strapDTO.setName(strapEntity.getName());
        strapDTO.setId(strapEntity.getId());
        return strapDTO;
    }
    public StrapEntity toEntity(StrapDTO strapDTO){
        StrapEntity strapEntity = new StrapEntity();
        strapEntity.setName(strapDTO.getName());
        return strapEntity;
    }
}
