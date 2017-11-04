/*
package com.food.delivery.controller;

import com.food.delivery.config.WebAppConfig;
import com.food.delivery.dto.CartDto;
import com.food.delivery.dto.CartItemDto;
import com.food.delivery.model.Ingredient;
import com.food.delivery.model.Product;
import com.food.delivery.service.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class CartRestControllerTest {

    @Autowired
    private WebApplicationContext app;
    @Autowired
    private MockHttpSession session;
    private MockMvc mockMvc;
    private List<Ingredient> ingredients;
    private Product product;
    @Autowired
    private CartService cartService;
    private CartDto cart;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(app).build();

        ingredients = Arrays.asList(
                new Ingredient("ING-1", "Banana"),
                new Ingredient("ING-2", "Papaya"),
                new Ingredient("ING-3", "Apple"),
                new Ingredient("ING-4", "Beet"),
                new Ingredient("ING-5", "Whole Milk"));

        product = new Product("P-000", "Mixed Fruit Shake 500mL",
                ingredients, "An amazing shake with mixed fruits",
                new BigDecimal(8), "Drink", true);

        CartItemDto item = new CartItemDto(session.getId() + product.getId(), product.getId());
        // Creates a new cart
        CartDto cart = new CartDto(session.getId());
        cart.addCartItem(item);
        // update database
//        cartService.save(cart);
    }


    // TODO: Test REST services

*/
/*    @Test
    public void readShouldReturnCorrectCartJson() throws Exception {
        // Arrange
        this.mockMvc.perform(put("/rest/cart/add/P1").session(session)).andExpect(status().is(200));

        // Action
        this.mockMvc.perform(get("/rest/cart/" + session.getId()).session(session)).andExpect(status().isOk()).
                andExpect(jsonPath("$.cartItems[0].product.productId").value("P1"));
    }*//*


    @Test
    public void readShouldReturnValueOfEarlyPost() throws Exception {
         */
/*Action
        mockMvc.perform(get("/rest/cart/111")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("111"));

        String cartJson = json(cart)*//*

    }


}*/
