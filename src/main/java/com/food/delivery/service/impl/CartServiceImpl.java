package com.food.delivery.service.impl;

import com.food.delivery.dto.CartDto;
import com.food.delivery.dto.CartItemDto;
import com.food.delivery.model.Cart;
import com.food.delivery.model.repository.mysql.CartRepository;
import com.food.delivery.service.CartService;
import com.food.delivery.service.ProductService;
import com.food.delivery.util.CartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mz on 19/07/17.
 */

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public void create(CartDto c) {
        cartRepository.create(c);
    }

    @Override
    public Cart read(String id) {
        CartDto cartDto = cartRepository.read(id);
        if(cartDto != null) {
            return CartFactory.create(cartDto, productService);
        }
        return null;
    }

    @Override
    public void update(CartDto c) {
        cartRepository.update(c);
    }

    @Override
    public void delete(String id) {
        cartRepository.delete(id);
    }

    @Override
    public void addItem(String cartId, String productId) {
        CartDto cart = cartRepository.read(cartId);
        if (cart == null) {
            // Cart does not exist, so creates a new cart item
            CartItemDto item = new CartItemDto(cartId + productId, productId);
            // Creates a new cart
            cart = new CartDto(cartId);
            cart.addCartItem(item);
            // update database
            create(cart);
            return;
        }

        CartItemDto item = cart.getCartItems().stream().filter(c -> c.getProductId().equals(productId)).findAny().orElse(null);
        if (item == null) {
            cart.addCartItem(new CartItemDto(cartId + productId, productId));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
        // update database
        create(cart);
    }

    @Override
    public void removeItem(String cartId, String productId) {
        CartDto cartDto = cartRepository.read(cartId);
        CartItemDto cartItemDto = cartDto.getCartItems().stream().filter(item -> item.getProductId().equals(productId)).findAny().orElse(null);
        cartDto.getCartItems().remove(cartItemDto);
        // update database
        create(cartDto);
    }

}

