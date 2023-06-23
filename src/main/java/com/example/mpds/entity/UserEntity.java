package com.example.mpds.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String permission;



}
