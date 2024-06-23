package com.example.mpds.mapper;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.UserEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class InvoiceMapper {
    public InvoiceEntity toEntity(InvoiceDTO dto, UserEntity user){
        InvoiceEntity entity=new InvoiceEntity();

//        CategoryEntity category=new CategoryEntity(); cai nay nen lay tu service ve roi dua vao entity sau
        entity.setTotalMoney(dto.getTotalMoney());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        //nen set khong hay de no default ??
        entity.setStatus(dto.getStatus());
        //set user??
        entity.setUser(user);
        entity.setPaymentMethod(dto.getPaymentMethod());

        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;

    }
    @SneakyThrows
    public InvoiceDTO toDTO(InvoiceEntity entity)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        String formattedString = formatter.format(entity.getCreateDate());

        InvoiceDTO dto=new InvoiceDTO();

        dto.setAddress(entity.getAddress());
        dto.setTotalMoney(entity.getTotalMoney());
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUserId(entity.getUser().getId());
        dto.setStatus(entity.getStatus());
        dto.setPhone(entity.getPhone());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setCreatedDate(formattedString);
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        return dto;
    }
}
