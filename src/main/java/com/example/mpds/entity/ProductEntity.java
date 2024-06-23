package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "catergoryId")
    private CategoryEntity category;
    @OneToMany(mappedBy = "product")
    private List<InvoiceInfoEntity> listInvoiceInfo;
    @OneToMany(mappedBy = "product")
    private List<ProductReviewEntity> listProductReview;
    @Column
    private String image1;
    @Column
    private String image2;
    @Column
    private String image3;

    @Column
    private String description;
    @Column(nullable = false)
    private int status; // check if this product is deleted by admin
    @Column
    private String type;
    @Column
    private String dialSize;



}
