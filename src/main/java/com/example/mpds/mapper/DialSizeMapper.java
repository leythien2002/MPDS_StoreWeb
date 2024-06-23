package com.example.mpds.mapper;

import com.example.mpds.dto.DialSizeDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.entity.DialSizeEntity;
import com.example.mpds.entity.TypeEntity;
import org.springframework.stereotype.Component;

@Component

public class DialSizeMapper {
    public DialSizeDTO toDTO(DialSizeEntity dialSizeEntity) {
        DialSizeDTO dialSizeDTO = new DialSizeDTO();
        dialSizeDTO.setName(dialSizeEntity.getName());
        dialSizeDTO.setId(dialSizeEntity.getId());
        return dialSizeDTO;
    }
    public DialSizeEntity toEntity(DialSizeDTO dialSizeDTO){
        DialSizeEntity dialSizeEntity = new DialSizeEntity();
        dialSizeEntity.setName(dialSizeDTO.getName());
        return dialSizeEntity;
    }
}
