package com.example.mpds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private ProductDTO product;
    private int quantity;
    private double price;


}
