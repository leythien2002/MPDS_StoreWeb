package com.example.mpds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private HashMap<Integer,CartItemDTO> itemList;
    private double total;


}
