package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEntity role;
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "user")
    private List<InvoiceEntity> listInvoice;

}
