package com.example.mpds.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Entity
@Table(name = "invoices")
public class InvoiceEntity extends BaseEntity{
    @Column
    private String userId;
    @Column
    private String totalMoney;
    @OneToMany(mappedBy = "idInvoice")
    private List<InvoiceInfoEntity> listInvoiceInfo;

    @Column
    @CreatedDate
    private String createDate;
    @Column
    private String phone;
    @Column
    private String address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
