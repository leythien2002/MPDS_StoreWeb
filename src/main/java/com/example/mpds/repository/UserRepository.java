package com.example.mpds.repository;

import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findOneByUserName(String username);

    UserEntity findByEmail(String email);




}
