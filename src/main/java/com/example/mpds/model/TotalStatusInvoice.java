package com.example.mpds.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalStatusInvoice {
    private Integer pendingStatus;
    private Integer paidStatus;
    private Integer cancelledStatus;
}
