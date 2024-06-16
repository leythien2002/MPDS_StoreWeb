package com.example.mpds.repository;

import com.example.mpds.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {
    ProductEntity findOneByName(String name);
    @Query("Select count(*) from ProductEntity p where p.status=1")
    long count();
    ProductEntity findOneById(int id);
//    @Query("SELECT p FROM ProductEntity p WHERE (:categories IS NULL OR p.category.id IN :categories) AND (:types IS NULL OR p.type IN :types) AND (:dialSizes IS NULL OR p.dialSize IN :dialSizes)")
//    Page<ProductEntity> findByCategoriesAndTypesAndDialSizes(@Param("categories") Integer categories, @Param("types") String types, @Param("dialSizes") String dialSizes, Pageable pageable);

//    @Query(value = "SELECT * FROM products p " +
//            "WHERE (:isNullCategories IS NULL OR p.catergory_id IN (:categories)) " +
//            "AND (:types IS NULL OR p.type IN (:types)) " +
//            "AND (:dialSizes IS NULL OR p.dial_size IN (:dialSizes))", nativeQuery = true)
//    Page<ProductEntity> findByCategoriesAndTypesAndDialSizes(@Param("categories") List<Integer> categories,
//                                                             @Param("types") List<String> types,
//                                                             @Param("dialSizes") List<String> dialSizes, Pageable pageable
//                                                             @Param("isNullCategories" )
//
//    );
    Page<ProductEntity> findAll(Specification<ProductEntity> spec, Pageable pageable);

    List<ProductEntity> findByCategory_Id(int CategoryId);
    





}
