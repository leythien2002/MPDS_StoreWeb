package com.example.mpds.dto;

import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.InvoiceInfoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends AbstractDTO{

//    private int  id;
    private String name;
    private Double price;
    private String categoryName;
    private String image1;
    private String image2;
    private String image3;
    private String description;

    //co can status ko ?
    private int status; // check if this product is deleted by admin

}
