package com.example.mpds.repository;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.DialSizeEntity;
import com.example.mpds.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialSizeRepository extends JpaRepository<DialSizeEntity,Long> {
    DialSizeEntity findByName(String name);

}
