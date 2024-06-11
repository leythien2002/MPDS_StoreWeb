package com.example.mpds.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_info")
public class InvoiceInfoEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="idInvoice")
    private InvoiceEntity invoice;
    @ManyToOne
    @JoinColumn(name="idProduct")
    private ProductEntity product;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    //neu khong co col price thì khi gia san pham thay doi, va luc tham chieu qua table khac de xac dinh gia thi se bi sai.



}
