package com.example.mpds.repository;

import com.example.mpds.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecifications {
    public static Specification<ProductEntity> hasCategories(List<String> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return null;
            }
            return root.get("category").get("name").in(categories);
        };
    }

    public static Specification<ProductEntity> hasTypes(List<String> types) {
        return (root, query, criteriaBuilder) -> {
            if (types == null || types.isEmpty()) {
                return null;
            }
            return root.get("type").get("name").in(types);
        };
    }

    public static Specification<ProductEntity> hasDialSizes(List<String> dialSizes) {
        return (root, query, criteriaBuilder) -> {
            if (dialSizes == null || dialSizes.isEmpty()) {
                return null;
            }
            return root.get("dialSize").get("name").in(dialSizes);
        };
    }

    public static Specification<ProductEntity> hasStraps(List<String> straps) {
        return (root, query, criteriaBuilder) -> {
            if (straps == null || straps.isEmpty()) {
                return null;
            }
            return root.get("strap").get("name").in(straps);
        };
    }
    public static Specification<ProductEntity> hasGenders(List<String> genders) {
        return (root, query, criteriaBuilder) -> {
            if (genders == null || genders.isEmpty()) {
                return null;
            }
            return root.get("gender").in(genders);
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
