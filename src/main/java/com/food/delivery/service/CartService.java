
package com.food.delivery.service;

import com.food.delivery.dto.CartDto;
import com.food.delivery.model.Cart;

import java.util.List;


/**
 * Created by mz on 19/07/17.
 */

public interface CartService {

    void create(CartDto c);

    Cart read(String id);

    void update(CartDto c);

    void delete(String id);

    void addItem(String cartId, String productId);

    void removeItem(String cartId, String productId);
}

