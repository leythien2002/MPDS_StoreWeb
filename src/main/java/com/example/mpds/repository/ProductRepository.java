package com.example.mpds.repository;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {
    ProductEntity findOneByName(String name);
    ProductEntity findOneById(int id);
    Page<ProductEntity> findAll(Specification<ProductEntity> spec, Pageable pageable);

}
