package com.example.mpds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceInfoDTO extends AbstractDTO{
    private int amount;
    private double price;
    private int invoiceId;
    private int productId;


}
