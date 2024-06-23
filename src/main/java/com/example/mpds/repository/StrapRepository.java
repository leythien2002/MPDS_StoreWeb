package com.example.mpds.repository;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.DialSizeEntity;
import com.example.mpds.entity.StrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrapRepository extends JpaRepository<StrapEntity,Long> {
    StrapEntity findByName(String name);

}
