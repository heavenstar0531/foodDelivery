package com.food.delivery.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mz on 19/07/17.
 * <p>
 * Data Transfer Object (DTO) for CartItem class.
 */
@Entity
@Table(name = "cart_item_dto")
public class CartItemDto {

    @Id
    private String id;
    private String productId;
    private int quantity;

    public CartItemDto() {
        // Empty constructor required for persistence framework
    }

    public CartItemDto(String id, String productId) {
        this.id = id;
        this.productId = productId;
        this.quantity = 1;
    }

    public CartItemDto(String id, String productId, int quantity) {
        this(id, productId);
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

