package com.example.mpds.services.impl;

import com.example.mpds.dto.StrapDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.StrapEntity;
import com.example.mpds.entity.TypeEntity;
import com.example.mpds.mapper.StrapMapper;
import com.example.mpds.mapper.TypeMapper;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.repository.StrapRepository;
import com.example.mpds.repository.TypeRepository;
import com.example.mpds.services.IStrapService;
import com.example.mpds.services.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService implements ITypeService {
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeMapper typeMapper;
    @Override
    public List<TypeDTO> getAll() {
        List<TypeDTO> list = new ArrayList<>();

        List<TypeEntity> lstType = typeRepository.findAll();
        for (TypeEntity entity : lstType) {
            list.add(typeMapper.toDTO(entity));
        }
        return list;
    }

    public String deleteById(Long id){
        List<ProductEntity>lstProd =productRepository.findByType_Id((id.intValue()));
        if(lstProd.isEmpty()){
            typeRepository.deleteById(id);
            return "Delete Suscessfully";
        }

        else {List<Integer> productIds = lstProd.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
            return  "Delete Failed, Product associated with type ID " + id + ": " + productIds;
//            System.out.println("Products associated with category ID " + id + ": " + productIds);
        }
    }
    public  void insertType(TypeDTO typeDTO){
        TypeEntity newType = typeMapper.toEntity(typeDTO);

        typeRepository.save(newType);
    }
    public void updateType(TypeDTO typeDTO)
    {
        TypeEntity existingType = typeRepository.findById((long) typeDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
        existingType.setName(typeDTO.getName());
        typeRepository.save(existingType);
    }
}
