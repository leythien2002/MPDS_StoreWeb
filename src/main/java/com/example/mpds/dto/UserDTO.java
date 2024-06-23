package com.example.mpds.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO{

    private String name;
    private String email;
    private String userName;
    private String password;
    private int roleId;

    private String phoneNumber;
    private String address;


}
