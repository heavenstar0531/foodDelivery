package com.food.delivery.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mz on 19/07/17.
 * <p>
 * Data Transfer Object (DTO) for Cart class.
 */
@Entity
@Table(name = "cart_dto")
public class CartDto {

    @Id
    private String id;
    @Transient
    private List<CartItemDto> cartItems;

/**
     * Empty constructor required for persistence framework
     */

    public CartDto() {
    }

    public CartDto(String id) {
        this.id = id;
        cartItems = new ArrayList<>();
    }

    public CartDto(String id, List<CartItemDto> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItemDto cartItemDto) {
        this.cartItems.add(cartItemDto);
    }
}

