package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> listProduct;

}
