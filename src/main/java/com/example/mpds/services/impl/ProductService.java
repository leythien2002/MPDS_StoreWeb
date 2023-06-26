package com.example.mpds.services.impl;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.mapper.ProductMapper;
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
    @Autowired
    private ProductMapper mapper;
    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> results=new ArrayList<>();

        List<ProductEntity> productList=productRepository.findAll(pageable).getContent();
        for(ProductEntity item:productList){
            ProductDTO productDTO=mapper.toDTO(item);
            results.add(productDTO);
        }
        return results;
    }
    public List<ProductDTO> findAll() {
        List<ProductDTO> results=new ArrayList<>();
        List<ProductEntity> productList=productRepository.findAll();
        for(ProductEntity item:productList){
            ProductDTO productDTO=mapper.toDTO(item);
            results.add(productDTO);
        }
        return results;
    }
    public ProductDTO findOne(String name){
        ProductDTO productDTO=new ProductDTO();
        ProductEntity product=productRepository.findOneByName(name);
        productDTO= mapper.toDTO(product);
        return productDTO;
    }

    public int totalProduct(){
        return (int) productRepository.count();
    }
}
