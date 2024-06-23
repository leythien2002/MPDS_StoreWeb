package com.example.mpds.repository;

import com.example.mpds.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecifications {
    public static Specification<ProductEntity> hasCategories(List<Integer> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return null;
            }
            return root.get("category").get("id").in(categories);
        };
    }

    public static Specification<ProductEntity> hasTypes(List<String> types) {
        return (root, query, criteriaBuilder) -> {
            if (types == null || types.isEmpty()) {
                return null;
            }
            return root.get("type").in(types);
        };
    }

    public static Specification<ProductEntity> hasDialSizes(List<String> dialSizes) {
        return (root, query, criteriaBuilder) -> {
            if (dialSizes == null || dialSizes.isEmpty()) {
                return null;
            }
            return root.get("dialSize").in(dialSizes);
        };
    }
    public static Specification<ProductEntity> nameContains(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword != null && !keyword.isEmpty()) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
            } else {
                return null;
            }
        };
    }
}
