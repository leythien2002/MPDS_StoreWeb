package com.example.mpds.services;

import com.example.mpds.dto.UserDTO;

public interface IUserService  {

    UserDTO findOne(String username);
    UserDTO save(UserDTO dto);

    void deleteById(String email);

}
