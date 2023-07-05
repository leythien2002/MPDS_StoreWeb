package com.example.mpds.services.impl;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.mapper.InvoiceMapper;
import com.example.mpds.repository.InvoiceRepository;
import com.example.mpds.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;

    public List<InvoiceDTO> findAll(){
        List<InvoiceDTO> result=new ArrayList<>();
        List<InvoiceEntity> list1=invoiceRepository.findAll();
        for(InvoiceEntity item: list1){
            result.add(invoiceMapper.toDTO(item));

        }
        return result;
    }


    //return DTO de update sau khi save ??
    public InvoiceEntity save(InvoiceDTO dto){
        InvoiceEntity entity=new InvoiceEntity();
        if(dto.getId()!=0){
           Optional<InvoiceEntity> tmp=invoiceRepository.findById(Long.valueOf(dto.getId()));
           InvoiceEntity oldInfor=new InvoiceEntity();
            if(tmp.isPresent()){
                oldInfor=tmp.get();
            }
//            entity= invoiceMapper.toEntity(dto,oldInfor);
        }
        else{
            entity=invoiceMapper.toEntity(dto);
        }
        entity=invoiceRepository.save(entity);
//        return invoiceMapper.toDTO(entity);
        return entity;
    }
}
