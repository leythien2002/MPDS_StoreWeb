package com.example.mpds.mapper;

import com.example.mpds.dto.ProductDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserDTO dto){
        UserEntity entity=new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setUserName(dto.getUserName());
        entity.setPermission(0);
    entity.setPassword(dto.getPassword());
        return entity;

    }
    public UserDTO toDTO(UserEntity entity){
        UserDTO dto=new UserDTO();
        dto.setId(entity.getId());//set id for add product to cart
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPermission(entity.getPermission());
        dto.setUserName(entity.getUserName());
        dto.setAddress(entity.getAddress());
        dto.setPassword(entity.getPassword());
        return dto;
    }
    //using for update ( store previous information)
    public UserEntity toEntity(UserDTO dto,UserEntity oldEntity){
        oldEntity.setPassword(dto.getPassword());
        oldEntity.setEmail(dto.getEmail());
        oldEntity.setName(dto.getName());
        oldEntity.setUserName(dto.getUserName());
        oldEntity.setPermission(dto.getPermission());
        return oldEntity;
    }

    //New user from register API

}
