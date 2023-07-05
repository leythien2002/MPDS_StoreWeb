package com.example.mpds.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "catergoryId")
    private CategoryEntity category;
    @OneToMany(mappedBy = "product")
    private List<InvoiceInfoEntity> listInvoiceInfo;
    @Column
    private String image1;
    @Column
    private String image2;
    @Column
    private String image3;

    @Column
    private String description;
    @Column(nullable = false)
    private int status; // check if this product is deleted by admin

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InvoiceInfoEntity> getListInvoiceInfo() {
        return listInvoiceInfo;
    }

    public void setListInvoiceInfo(List<InvoiceInfoEntity> listInvoiceInfo) {
        this.listInvoiceInfo = listInvoiceInfo;
    }
}
