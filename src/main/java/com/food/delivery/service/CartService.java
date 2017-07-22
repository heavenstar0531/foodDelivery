package com.food.delivery.service;

import com.food.delivery.dto.CartDto;
import com.food.delivery.model.Cart;

import java.util.List;

/**
 * Created by mz on 19/07/17.
 */
public interface CartService {

    void save(CartDto c);

    void save(Iterable<CartDto> it);

    Cart findOne(String id);

    void delete(String id);

    void addItem(String cartId, String productId);

    void removeItem(String cartId, String productId);
}
