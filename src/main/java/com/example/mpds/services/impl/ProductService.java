package com.example.mpds.services.impl;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> results=new ArrayList<>();

        List<ProductEntity> productList=productRepository.findAll();
        return null;
    }
}
