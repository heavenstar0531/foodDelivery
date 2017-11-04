
package com.food.delivery.util;

import com.food.delivery.dto.CartDto;
import com.food.delivery.model.Cart;
import com.food.delivery.model.CartItem;
import com.food.delivery.model.Product;
import com.food.delivery.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class CartFactory {

    private CartFactory () {
        // no instances
    }

    /**
     * Creates a Cart object from a DTO version
     */

    public static Cart create(CartDto cartDto, ProductService productService) {
        List<CartItem> cartItems = new ArrayList<>();
        Cart cart = new Cart(cartDto.getId(), cartItems);

        cartDto.getCartItems().forEach(cartItemDto -> {
            Product p = productService.read(cartItemDto.getProductId());
            cartItems.add(new CartItem(cartItemDto.getId(), p, cartItemDto.getQuantity()));
        });

        return cart;
    }
}

