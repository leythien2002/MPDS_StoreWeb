package com.example.mpds.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "invoices")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<InvoiceInfoEntity> getListInvoiceInfo() {
        return listInvoiceInfo;
    }

    public void setListInvoiceInfo(List<InvoiceInfoEntity> listInvoiceInfo) {
        this.listInvoiceInfo = listInvoiceInfo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
