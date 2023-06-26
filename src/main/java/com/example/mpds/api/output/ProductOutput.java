package com.example.mpds.api.output;

import com.example.mpds.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductOutput {

    private int currentPage;
    private int totalPage;
    private List<ProductDTO> listResult=new ArrayList<>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ProductDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<ProductDTO> listResult) {
        this.listResult = listResult;
    }
}
