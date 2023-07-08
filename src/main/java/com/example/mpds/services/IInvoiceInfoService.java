package com.example.mpds.services;

import com.example.mpds.dto.InvoiceInfoDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductEntity;

public interface IInvoiceInfoService {
    void save(InvoiceInfoDTO dto);
}
