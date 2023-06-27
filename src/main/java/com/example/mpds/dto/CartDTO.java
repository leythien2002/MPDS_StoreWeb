package com.example.mpds.dto;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> cart;

    public List<CartItemDTO> getCart() {
        return cart;
    }

    public void setCart(List<CartItemDTO> cart) {
        this.cart = cart;
    }
}
