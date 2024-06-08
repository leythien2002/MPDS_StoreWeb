package com.example.mpds.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(name = "username",nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int permission;
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name="address")
    private String address;


    @OneToMany(mappedBy = "user")
    private List<InvoiceEntity> listInvoice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InvoiceEntity> getListInvoice() {
        return listInvoice;
    }

    public void setListInvoice(List<InvoiceEntity> listInvoice) {
        this.listInvoice = listInvoice;
    }
}
