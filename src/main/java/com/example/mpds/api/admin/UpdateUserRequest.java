package com.example.mpds.api.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private int id;
    private String userEmail;
    private String roleName;
    private String phoneNumber;
    private String address;
}
