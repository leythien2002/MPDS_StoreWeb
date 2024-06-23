package com.example.mpds.model;

import com.example.mpds.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInvoiceResult {
    private long totalItem;
    private List<InvoiceDTO> invoices;
}
