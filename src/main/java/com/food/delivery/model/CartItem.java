package com.food.delivery.model;

import java.math.BigDecimal;

/**
 * Created by mz on 19/07/17.
 * <p>
 * Represents an item type in user's shopping cart.
 */
public class CartItem {

    private String id;
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem(String id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        quantity = 1;
        updatePrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        updatePrice();
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updatePrice() {
        totalPrice = product.getPrice().multiply(new BigDecimal(quantity));
    }
}
