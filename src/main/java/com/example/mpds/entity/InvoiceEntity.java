package com.example.mpds.entity;

import com.example.mpds.config.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // su dung cho annotation @CreateDate/ @CreateBy ...
public class InvoiceEntity extends BaseEntity {
    //    @Column(name = "userid",nullable = false)
//    private String userId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @Column(name = "totalmoney", nullable = false)
    private double totalMoney;
    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceInfoEntity> listInvoiceInfo;
    @Column(name = "status")
    private String status;
    @Column(name = "email")
    private String email;
    @Column(name = "created_date")
    @CreatedDate
    private Date createDate;
    @Column(name = "updated_date")
    @CreatedDate
    private Date updatedDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String paymentMethod;
    @PrePersist
    public void prePersist() {
        createDate = createDate != null ? createDate : new Date();
        updatedDate = updatedDate != null ? updatedDate : new Date();
    }
    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }


}
