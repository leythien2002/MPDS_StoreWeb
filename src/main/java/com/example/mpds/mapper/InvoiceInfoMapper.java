package com.example.mpds.mapper;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.InvoiceInfoDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceInfoMapper {
    public InvoiceInfoEntity toEntity(InvoiceInfoDTO dto){
        InvoiceInfoEntity entity=new InvoiceInfoEntity();
        entity.setAmount(dto.getAmount());
        entity.setPrice(dto.getPrice());

        return entity;

    }
    public InvoiceInfoDTO toDTO(InvoiceInfoEntity entity){
        InvoiceInfoDTO dto=new InvoiceInfoDTO();
        dto.setId(entity.getId());
        dto.setInvoiceId(entity.getInvoice().getId());
        dto.setAmount(entity.getAmount());
        dto.setPrice(entity.getPrice());
        dto.setProductId(entity.getProduct().getId());

        return dto;
    }
}
