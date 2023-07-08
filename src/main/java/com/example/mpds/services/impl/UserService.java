package com.example.mpds.services.impl;

import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.UserMapper;
import com.example.mpds.repository.UserRepository;
import com.example.mpds.services.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDTO findOne(String username){
        UserDTO userDTO=new UserDTO();
        UserEntity entity=userRepository.findOneByUserName(username);
        userDTO=userMapper.toDTO(entity);
        return userDTO;
    }
    public UserEntity findUser(String username){

        UserEntity entity=userRepository.findOneByUserName(username);

        return entity;
    }
    public UserDTO save(UserDTO dto){
        UserEntity entity=new UserEntity();
        //combine update and add.
        if(dto.getId()!=0){//can't compare int with null
            Optional<UserEntity> tmp=userRepository.findById((long) dto.getId());
            UserEntity oldInfor=new UserEntity();
            if(tmp.isPresent()){
                oldInfor=tmp.get();
            }
            entity= userMapper.toEntity(dto,oldInfor);
        }
        else{
            entity=userMapper.toEntity(dto);
        }
        entity=userRepository.save(entity);
        return userMapper.toDTO(entity);
    }


}
