package com.example.mpds.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    @Column
    private String name;
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> listProduct;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductEntity> listProduct) {
        this.listProduct = listProduct;
    }
}
