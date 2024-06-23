package com.example.mpds.services.impl;

import com.example.mpds.dto.ProductDTO;

import java.util.List;

public class FilterProductResult {
    private List<ProductDTO> listProductDTO;
    private long totalElements;

    public List<ProductDTO> getListProductDTO() {
        return listProductDTO;
    }

    public void setListProductDTO(List<ProductDTO> listProductDTO) {
        this.listProductDTO = listProductDTO;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public FilterProductResult(List<ProductDTO> listProductDTO, long totalElements) {
        this.listProductDTO = listProductDTO;
        this.totalElements = totalElements;
    }
}
