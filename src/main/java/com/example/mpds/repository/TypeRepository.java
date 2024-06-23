package com.example.mpds.repository;

import com.example.mpds.entity.DialSizeEntity;
import com.example.mpds.entity.StrapEntity;
import com.example.mpds.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,Long> {
    TypeEntity findByName(String name);

}
