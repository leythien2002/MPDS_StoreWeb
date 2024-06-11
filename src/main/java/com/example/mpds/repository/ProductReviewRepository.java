package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository  extends JpaRepository<ProductReviewEntity,Long> {
}
