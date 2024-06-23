package com.example.mpds.repository;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity,Long> {

//    CategoryEntity findById(Long id);
    CategoryEntity findByName(String name);

}
