package com.example.mpds.services.impl;

import com.example.mpds.api.admin.UpdateUserRequest;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.UserMapper;
import com.example.mpds.repository.UserRepository;
import com.example.mpds.services.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDTO findOne(String username){

        UserEntity entity=userRepository.findOneByUserName(username).orElseThrow(()->new RuntimeException("Entity Not Found"));
        return userMapper.toDTO(entity);

    }
    public UserDTO findByUserId(Long id){

        UserEntity entity=userRepository.findById(id).orElseThrow(()->new RuntimeException("Entity Not Found"));
        return userMapper.toDTO(entity);

    }
    public UserEntity findUserById(Long id){

        UserEntity entity=userRepository.findById(id).orElseThrow(()->new RuntimeException("Entity Not Found"));
        return entity;

    }
    public UserEntity findUser(String username){

        UserEntity entity=userRepository.findOneByUserName(username).orElseThrow(()->new RuntimeException("Entity Not Found"));

        return entity;
    }
    public UserDTO updateProfile(UserDTO dto){
        UserEntity entity=userRepository.findOneByUserName(dto.getUserName()).orElseThrow(()->new RuntimeException("Entity Not Found"));
        updateIfNotNull(dto.getName(), entity::setName);
        updateIfNotNull(dto.getEmail(), entity::setEmail);
        updateIfNotNull(dto.getAddress(), entity::setAddress);
        Optional.ofNullable(dto.getPassword())
                .ifPresent(password -> {
                    if (password.equals(entity.getPassword())) {
                        entity.setPassword(password);
                    } else {
                        throw new IllegalArgumentException("Passwords do not match");
                    }
                });
        return userMapper.toDTO(userRepository.save(entity));
    }
    public void updatePassword(UserDTO dto){
        UserEntity entity=userRepository.findOneByUserName(dto.getUserName()).orElseThrow(()->new RuntimeException("Entity Not Found"));
        updateIfNotNull(dto.getPassword(), entity::setPassword);
        userRepository.save(entity);
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
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(String email){
//        userRepository.deleteById(Id);
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public void updateUser(UpdateUserRequest user){

        UserEntity existingUser = userRepository.findById((long) user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setEmail(user.getUserEmail());
        existingUser.setPermission(user.getIsUser());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAddress(user.getAddress());

        userRepository.save(existingUser);
    }
    private <T> void updateIfNotNull(T value, Consumer<T> updater) {
        Optional.ofNullable(value).ifPresent(updater);
    }

}
