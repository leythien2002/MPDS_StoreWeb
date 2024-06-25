package com.example.mpds.services.impl;


import com.example.mpds.dto.InvoiceDTO;

import com.example.mpds.dto.InvoiceInfoDTO;
import com.example.mpds.entity.InvoiceEntity;

import com.example.mpds.entity.InvoiceInfoEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.InvoiceInfoMapper;
import com.example.mpds.repository.InvoiceInfoRepository;
import com.example.mpds.services.IInvoiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceInfoService implements IInvoiceInfoService {
    @Autowired
    private InvoiceInfoRepository invoiceInfoRepository;
    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;

    public List<ProductEntity> getInfo(long id) {
        List<InvoiceInfoEntity> invoiceInfo=invoiceInfoRepository.findByInvoiceId(id);
        List<ProductEntity> products= new ArrayList<>();
        for (InvoiceInfoEntity invoiceInfoEntity : invoiceInfo) {
            products.add(invoiceInfoEntity.getProduct());
        }
        return products;
    };

    public InvoiceInfoEntity save(InvoiceInfoDTO dto){
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
            entity=invoiceInfoMapper.toEntity(dto,invoiceService.findInvoice(dto.getInvoiceId()),productService.findProduct(dto.getProductId()));
        }
        return invoiceInfoRepository.save(entity);
//        return invoiceMapper.toDTO(entity);

    }
}
