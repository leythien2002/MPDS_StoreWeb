package com.example.mpds.services.impl;

import com.example.mpds.api.InvoiceAPI;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.ProductReviewEntity;
import com.example.mpds.repository.ProductRepository;
import com.example.mpds.repository.ProductReviewRepository;
import com.example.mpds.services.IProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService implements IProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;
    @Autowired
    private ProductRepository productRepository;
    public ProductReviewEntity saveProductReview(InvoiceAPI.Review review) {
        ProductEntity product= productRepository.findOneById(review.getProductId());
        ProductReviewEntity productReview = new ProductReviewEntity(product,review.getUserId(),review.getReview(), review.getUserName());
        return productReviewRepository.save(productReview);
    }
}
