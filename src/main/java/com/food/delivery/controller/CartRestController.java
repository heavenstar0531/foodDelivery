package com.food.delivery.controller;

import com.food.delivery.dto.CartDto;
import com.food.delivery.model.Cart;
import com.food.delivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("rest/cart")
public class CartRestController {

    private final CartService cartService;

    @Autowired
    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CartDto cartDto) {
        cartService.save(cartDto);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartService.findOne(cartId);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
        cartDto.setId(cartId);
        cartService.save(cartDto);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "cartId") String cartId) {
        cartService.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    public void addItem(@PathVariable String productId, HttpSession session) {
        cartService.addItem(session.getId(), productId);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    public void removeItem(@PathVariable String productId, HttpSession session) {
        cartService.removeItem(session.getId(), productId);
    }
}
