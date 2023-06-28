package com.example.mpds.dto;

import java.util.HashMap;


public class CartDTO {

    private HashMap<Integer,CartItemDTO> itemList;
    private double total;

    public HashMap<Integer, CartItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(HashMap<Integer, CartItemDTO> itemList) {
        this.itemList = itemList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
