package com.example.mpds.services;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.services.impl.FilterProductResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
   FilterProductResult findAll(List<String> categories, List<String> types, List<String> dialSizes,List<String> straps,List<String> gender, Pageable pageable, String searchText);
    List<ProductDTO> findAll();
     ProductDTO findOne(int id);
     int totalProduct();
    ProductDTO findOne(String name);
}
