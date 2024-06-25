package com.example.mpds.services;

import com.example.mpds.dto.InvoiceInfoDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import com.example.mpds.entity.ProductEntity;

public interface IInvoiceInfoService {
    InvoiceInfoEntity save(InvoiceInfoDTO dto);
}
