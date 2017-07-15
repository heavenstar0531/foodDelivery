package controller;

import com.food.delivery.config.WebAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mz on 15/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class IngredientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * FAIL
     * The performed url shows a list of ingredients.
     * The List attribute is sent to ingredients.jsp through Model class.
     */
    @Test
    public void attributeIngredientListShouldNotExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients"))
                .andExpect(model().attributeDoesNotExist("ingredient"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeIngredientsListShouldExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients"))
                .andExpect(model().attributeExists("ingredients"));
    }

    /**
     * FAIL
     * The performed url instantiates an Ingredient object with @ModelAttribute in addIngredient.jsp
     */
    @Test
    public void attributeNewIngredientShouldNotExist() throws Exception {
        mockMvc.perform(get("/admin/ingredients/add"))
                .andExpect(model().attributeDoesNotExist("newIngredients"));
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
     * The performed url redirects to the same path after POST method.
     */
    @Test
    public void ingredientsAddShouldRedirectToTheSamePathAfterPostMethod() throws Exception {
        mockMvc.perform(post("/admin/ingredients/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/ingredients/add"));
    }
}
