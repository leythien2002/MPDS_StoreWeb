package com.example.mpds.entity;

import jakarta.persistence.*;

@Entity
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


    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity idInvoice) {
        this.invoice = idInvoice;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity idProduct) {
        this.product = idProduct;
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
