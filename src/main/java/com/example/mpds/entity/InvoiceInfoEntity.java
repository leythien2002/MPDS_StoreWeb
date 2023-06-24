package com.example.mpds.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_info")
public class InvoiceInfoEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="idInvoice")
    private InvoiceEntity idInvoice;
    @ManyToOne
    @JoinColumn(name="idProduct")
    private ProductEntity idProduct;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    //neu khong co col price thì khi gia san pham thay doi, va luc tham chieu qua table khac de xac dinh gia thi se bi sai.


    public InvoiceEntity getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(InvoiceEntity idInvoice) {
        this.idInvoice = idInvoice;
    }

    public ProductEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductEntity idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
