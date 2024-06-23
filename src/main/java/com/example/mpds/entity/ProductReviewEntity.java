package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_REVIEW")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewEntity extends BaseEntity {
    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductEntity product;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "review")
    private String review;
    @Column(name="user_name")
    private String userName;

}
