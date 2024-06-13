package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository  extends JpaRepository<ProductReviewEntity,Long> {
    List<ProductReviewEntity> findByProductId(int productId);

}
