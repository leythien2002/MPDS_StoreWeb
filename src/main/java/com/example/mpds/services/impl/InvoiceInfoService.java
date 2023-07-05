package com.example.mpds.services.impl;


import com.example.mpds.dto.InvoiceDTO;

import com.example.mpds.dto.InvoiceInfoDTO;
import com.example.mpds.entity.InvoiceEntity;

import com.example.mpds.entity.InvoiceInfoEntity;
import com.example.mpds.mapper.InvoiceInfoMapper;
import com.example.mpds.repository.InvoiceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceInfoService {
    @Autowired
    private InvoiceInfoRepository invoiceInfoRepository;
    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;
    public void save(InvoiceInfoDTO dto){
        InvoiceInfoEntity entity=new InvoiceInfoEntity();
        if(dto.getId()!=0){
            Optional<InvoiceInfoEntity> tmp=invoiceInfoRepository.findById(Long.valueOf(dto.getId()));
            InvoiceInfoEntity oldInfor=new InvoiceInfoEntity();
            if(tmp.isPresent()){
                oldInfor=tmp.get();
            }
//            entity= invoiceMapper.toEntity(dto,oldInfor);
        }
        else{
            entity=invoiceInfoMapper.toEntity(dto);
        }
        entity=invoiceInfoRepository.save(entity);
//        return invoiceMapper.toDTO(entity);

    }
}
