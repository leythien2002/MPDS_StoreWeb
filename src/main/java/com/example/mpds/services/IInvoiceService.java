package com.example.mpds.services;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.UserEntity;

import java.text.ParseException;
import java.util.List;

public interface IInvoiceService {
    List<InvoiceDTO> findAll() throws ParseException;
    InvoiceEntity save(InvoiceDTO dto, int userId);
}
