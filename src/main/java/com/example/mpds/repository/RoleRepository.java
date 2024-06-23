package com.example.mpds.repository;

import com.example.mpds.entity.RoleEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRoleName(String roleName);
}
