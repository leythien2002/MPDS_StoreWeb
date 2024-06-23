package com.example.mpds.dto;

import com.example.mpds.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    private String createdDate;

    private String paymentMethod;
    private String createdBy;
    private String updatedBy;
}
