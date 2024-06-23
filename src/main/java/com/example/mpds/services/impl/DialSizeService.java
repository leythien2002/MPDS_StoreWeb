package com.example.mpds.services.impl;

import com.example.mpds.dto.DialSizeDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.entity.DialSizeEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.TypeEntity;
import com.example.mpds.mapper.DialSizeMapper;
import com.example.mpds.mapper.TypeMapper;
import com.example.mpds.repository.DialSizeRepository;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.repository.TypeRepository;
import com.example.mpds.services.IDialSizeService;
import com.example.mpds.services.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialSizeService implements IDialSizeService {
    @Autowired
    DialSizeRepository dialSizeRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DialSizeMapper dialSizeMapper;
    @Override
    public List<DialSizeDTO> getAll() {
        List<DialSizeDTO> list = new ArrayList<>();

        List<DialSizeEntity> lstDialSize = dialSizeRepository.findAll();
        for (DialSizeEntity entity : lstDialSize) {
            list.add(dialSizeMapper.toDTO(entity));
        }
        return list;
    }

    public String deleteById(Long id){
        List<ProductEntity>lstProd =productRepository.findByDialSize_Id((id.intValue()));
        if(lstProd.isEmpty()){
            dialSizeRepository.deleteById(id);
            return "Delete Suscessfully";
        }

        else {List<Integer> productIds = lstProd.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
            return  "Delete Failed, Product associated with dial size ID " + id + ": " + productIds;
//            System.out.println("Products associated with category ID " + id + ": " + productIds);
        }
    }
    public  void insertDialSize(DialSizeDTO dialSizeDTO){
        DialSizeEntity newDialSize = dialSizeMapper.toEntity(dialSizeDTO);

        dialSizeRepository.save(newDialSize);
    }
    public void updateDialSize(DialSizeDTO dialSizeDTO)
    {
        DialSizeEntity existingDialSize = dialSizeRepository.findById((long) dialSizeDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Dial Size not found"));
        existingDialSize.setName(dialSizeDTO.getName());
        dialSizeRepository.save(existingDialSize);
    }
}
