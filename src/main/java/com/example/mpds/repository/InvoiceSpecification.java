package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class InvoiceSpecification {
    public static Specification<InvoiceEntity> hasStatus(String status) {
        return (Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<InvoiceEntity> createDateBetween(Date startDate, Date endDate) {
        return (Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (startDate != null && endDate != null) {
                return builder.between(root.get("createDate"), startDate, endDate);
            } else if (startDate != null) {
                return builder.greaterThanOrEqualTo(root.get("createDate"), startDate);
            } else if (endDate != null) {
                return builder.lessThanOrEqualTo(root.get("createDate"), endDate);
            } else {
                return builder.conjunction();
            }
        };
    }
}
