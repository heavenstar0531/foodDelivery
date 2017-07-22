package com.food.delivery.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Created by mz on 19/07/17.
 * <p>
 * Represents a shopping cart.
 */
public class Cart {

    private String id;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;

    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        updateTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updateTotalPrice() {
        Function<CartItem, BigDecimal> totalMapper = CartItem::getTotalPrice;
        BigDecimal totalPrice = cartItems.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalPrice(totalPrice);
    }
}
