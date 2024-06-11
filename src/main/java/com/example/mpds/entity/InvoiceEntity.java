package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // su dung cho annotation @CreateDate/ @CreateBy ...
public class InvoiceEntity extends BaseEntity{
//    @Column(name = "userid",nullable = false)
//    private String userId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @Column(name = "totalmoney",nullable = false)
    private double totalMoney;
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceInfoEntity> listInvoiceInfo;
    @Column(name="status")
    private String status;
    @Column(name="email")
    private String email;
    @Column(name = "createdate")
    @CreatedDate
    private String createDate;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;



}
