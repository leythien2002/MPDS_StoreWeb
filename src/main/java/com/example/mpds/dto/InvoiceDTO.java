package com.example.mpds.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO extends AbstractDTO{

    private int userId;

    private double totalMoney;
    private String email;

    private String phone;

    private String address;
    private String status;
    private String createDate;
    private String paymentMethod;




}
