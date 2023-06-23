package com.example.mpds.services;

import com.example.mpds.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll(Pageable pageable);
}
