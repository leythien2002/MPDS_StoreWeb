package com.example.mpds.services;

import com.example.mpds.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findAll();
     ProductDTO findOne(int id);
     int totalProduct();
    ProductDTO findOne(String name);
}
