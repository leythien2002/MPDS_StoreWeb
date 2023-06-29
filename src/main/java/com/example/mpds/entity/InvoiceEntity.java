package com.example.mpds.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Entity
@Table(name = "invoices")
public class InvoiceEntity extends BaseEntity{
//    @Column(name = "userid",nullable = false)
//    private String userId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @Column(name = "totalmoney",nullable = false)
    private String totalMoney;
    @OneToMany(mappedBy = "idInvoice")
    private List<InvoiceInfoEntity> listInvoiceInfo;

    @Column(name = "createdate")
    @CreatedDate
    private String createDate;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
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
