package com.example.mpds.services.impl;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.StrapDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.StrapEntity;
import com.example.mpds.mapper.StrapMapper;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.repository.StrapRepository;
import com.example.mpds.services.IStrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StrapService implements IStrapService {
    @Autowired
    StrapRepository strapRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    StrapMapper strapMapper;
    @Override
    public List<StrapDTO> getAll() {
        List<StrapDTO> listStrapDTO = new ArrayList<>();
        List<StrapEntity> lstStrap = strapRepository.findAll();
        for (StrapEntity entity : lstStrap) {
            listStrapDTO.add(strapMapper.toDTO(entity));
        }
        return listStrapDTO;
    }

    public String deleteById(Long id){
        List<ProductEntity>lstProd =productRepository.findByStrap_Id((id.intValue()));
        if(lstProd.isEmpty()){
            strapRepository.deleteById(id);
            return "Delete Suscessfully";
        }

        else {List<Integer> productIds = lstProd.stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
            return  "Delete Failed, Product associated with strap ID " + id + ": " + productIds;
//            System.out.println("Products associated with category ID " + id + ": " + productIds);
        }
    }
    public  void insertStrap(StrapDTO strapDTO){
        StrapEntity newStrap = strapMapper.toEntity(strapDTO);

        strapRepository.save(newStrap);
    }
    public void updateStrap(StrapDTO strapDTO)
    {
        StrapEntity existingStrap = strapRepository.findById((long) strapDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Strap not found"));
        existingStrap.setName(strapDTO.getName());
        strapRepository.save(existingStrap);
    }
}
