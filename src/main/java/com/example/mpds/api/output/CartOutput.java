package com.example.mpds.api.output;

public class CartOutput {
    private int cartSize;
    private int quantityProduct;
    private double totalPrice;
    private double totalPriceOfCart;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceOfCart() {
        return totalPriceOfCart;
    }

    public void setTotalPriceOfCart(double totalPriceOfCart) {
        this.totalPriceOfCart = totalPriceOfCart;
    }


    public int getCartSize() {
        return cartSize;
    }

    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
