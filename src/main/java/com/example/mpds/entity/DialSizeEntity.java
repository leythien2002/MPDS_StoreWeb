package com.example.mpds.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "dial_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DialSizeEntity extends  BaseEntity{
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "dialSize")
    private List<ProductEntity> listProduct;
}
