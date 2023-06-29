package com.example.mpds.services;

import com.example.mpds.dto.UserDTO;

public interface IUserService  {

    UserDTO findOne(String username, String password);
    UserDTO save(UserDTO dto);

}
