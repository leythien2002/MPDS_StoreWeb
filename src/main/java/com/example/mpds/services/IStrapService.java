package com.example.mpds.services;

import com.example.mpds.dto.CategoryDTO;
import com.example.mpds.dto.StrapDTO;
import com.example.mpds.dto.TypeDTO;
import com.example.mpds.entity.StrapEntity;

import java.util.List;

public interface IStrapService {
    public List<StrapDTO> getAll();
}
