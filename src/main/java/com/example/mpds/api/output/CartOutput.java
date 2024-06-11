package com.example.mpds.api.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartOutput {
    private int cartSize;
    private int quantityProduct;
    private double totalPrice;
    private double totalPriceOfCart;


}
