package com.food.delivery.controller;

import com.food.delivery.dto.CartDto;
import com.food.delivery.dto.CartItemDto;
import com.food.delivery.model.Cart;
import com.food.delivery.service.CartService;
import com.food.delivery.service.ProductService;
import com.food.delivery.util.CartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest/cart")
public class CartRestController {

    // TODO: Make the shopping cart work with services. Currently, it is now possible to add product to it

    private final CartService cartService;
    private final ProductService productService;
    private final List<CartDto> cartDtoList = new ArrayList<>();

    @Autowired
    public CartRestController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CartDto cartDto) {
        // TODO: Persist in database
        cartDtoList.add(cartDto);
//        cartService.create(cartDto);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public Cart read(@PathVariable(value = "cartId") String cartId) {
        // TODO: Persist in database
        CartDto cartDto = cartDtoList.stream().filter(c -> c.getId().equals(cartId)).findFirst().orElse(null);
        // return cartService.read(cartId);
        return CartFactory.create(cartDto, productService);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
        // TODO: Persist in database
        cartDto.setId(cartId);
        cartDtoList.removeIf(c -> c.getId().equals(cartDto.getId()));
//        cartService.update(cartDto);
        cartDtoList.add(cartDto);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "cartId") String cartId) {
        // TODO: Persist in database
//        cartService.delete(cartId);
        cartDtoList.removeIf(c -> c.getId().equals(cartId));
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    public void addItem(@PathVariable String productId, HttpSession session) {
        // TODO: Persist in database
        CartDto dto = cartDtoList.stream().filter(c -> c.getId().equals(session.getId())).findFirst().orElse(null);
        if (dto != null) {
            dto.addCartItem(new CartItemDto(session.getId() + productId, productId));
        }
//        cartService.addItem(session.getId(), productId);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    public void removeItem(@PathVariable String productId, HttpSession session) {
        // TODO: Persist in database
        CartDto cart = cartDtoList.stream().filter(c -> c.getId().equals(session.getId())).findFirst().orElse(null);
        if (cart != null) {
            CartItemDto item = cart.getCartItems().stream().filter(i -> i.getId().equals(productId)).findFirst().orElse(null);
            if (item != null) {
                cart.getCartItems().remove(item);
            }
        }
//        cartService.removeItem(session.getId(), productId);
    }
}

