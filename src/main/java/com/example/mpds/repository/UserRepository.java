package com.example.mpds.repository;

import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findOneByUserName(String username);

    UserEntity findByEmail(String email);



}
