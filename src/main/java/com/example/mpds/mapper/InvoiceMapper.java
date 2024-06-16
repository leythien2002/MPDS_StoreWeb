package com.example.mpds.mapper;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public InvoiceEntity toEntity(InvoiceDTO dto, UserEntity user){
        InvoiceEntity entity=new InvoiceEntity();
//        CategoryEntity category=new CategoryEntity(); cai nay nen lay tu service ve roi dua vao entity sau
        entity.setTotalMoney(dto.getTotalMoney());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        entity.setCreateDate(dto.getCreateDate());
        //nen set khong hay de no default ??
        entity.setStatus(dto.getStatus());
        //set user??
        entity.setUser(user);
        entity.setPaymentMethod(dto.getPaymentMethod());
        return entity;

    }
    public InvoiceDTO toDTO(InvoiceEntity entity){
        InvoiceDTO dto=new InvoiceDTO();
        dto.setAddress(entity.getAddress());
        dto.setCreateDate(entity.getCreateDate());
        dto.setTotalMoney(entity.getTotalMoney());
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUserId(entity.getUser().getId());
        dto.setStatus(entity.getStatus());
        dto.setPhone(entity.getPhone());
        dto.setPaymentMethod(entity.getPaymentMethod());
        return dto;
    }
}
