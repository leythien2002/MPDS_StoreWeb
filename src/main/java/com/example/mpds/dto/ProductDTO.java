package com.example.mpds.dto;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;


public class ProductDTO extends AbstractDTO{

//    private int  id;
    private String name;
    private Double price;
    private String categoryName;
    private String image1;
    private String image2;
    private String image3;
    private String description;

    private String type;
    private String dialSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDialSize() {
        return dialSize;
    }

    public void setDialSize(String dialSize) {
        this.dialSize = dialSize;
    }

    //co can status ko ?
    private int status; // check if this product is deleted by admin

//    @Override
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
