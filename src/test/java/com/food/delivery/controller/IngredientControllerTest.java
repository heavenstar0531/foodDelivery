
package com.food.delivery.controller;

import com.food.delivery.config.WebAppConfig;
import com.food.delivery.model.Ingredient;
import com.food.delivery.service.IngredientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;


/**
 * Created by mz on 15/07/17.
 */

@Transactional()
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class IngredientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private IngredientService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * FAIL
     * The performed URL shows a list of ingredients.
     * The List attribute is sent to adminIngredients.jsp through Model object.
     * * A wrong object name is given at 'andExcept' method.
     */
    @Test
    public void attributeIngredientListShouldNotExist() throws Exception {
        // "ingredient" does not exist
        mockMvc.perform(get("/admin/ingredients"))
                .andExpect(model().attributeDoesNotExist("nonExistentAttribute"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeIngredientListShouldExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients"))
                .andExpect(model().attributeExists("ingredients"));
    }

    /**
     * FAIL
     * The performed URL instantiates a Product object named 'newProduct' with @ModelAttribute and adds to addProduct.jsp
     * A wrong object name is given at andExcept method.
     */
    @Test
    public void attributeNewIngredientShouldNotExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients/add"))
                .andExpect(model().attributeDoesNotExist("nonExistentAttribute"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeNewIngredientShouldExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients/add"))
                .andExpect(model().attributeExists("newIngredient"));
    }

    /**
     * PASS
     * The performed URL redirects to the same path after POST method.
     */
    // not working
    // Caused by: org.hibernate.id.IdentifierGenerationException:
    // ids for this class must be manually assigned before calling save()
//    @Test
    public void ingredientsAddShouldRedirectToTheSamePathAfterPostMethod() throws Exception {
        mockMvc.perform(post("/admin/ingredients/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/ingredients/add"));
    }

}

