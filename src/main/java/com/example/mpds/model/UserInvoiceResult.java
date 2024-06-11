package com.example.mpds.model;

import com.example.mpds.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInvoiceResult {
    private long totalPage;
    private List<InvoiceDTO> invoices;
}
