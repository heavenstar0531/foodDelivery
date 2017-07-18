package controller;

import com.food.delivery.config.WebAppConfig;
import com.food.delivery.model.Ingredient;
import com.food.delivery.model.Product;
import com.food.delivery.service.ProductService;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mz on 17/07/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ProductService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * FAIL
     * The performed URL shows a list of products.
     * The List attribute is sent to products.jsp through Model object.
     * A wrong object name is given at 'andExcept' method.
     */
    @Test
    public void attributeProductListShouldNotExist() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(model().attributeDoesNotExist("nonExistentAttribute"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeProductListShouldExist() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(model().attributeExists("products"));
    }

    /**
     * FAIL
     * A wrong object name is given at 'andExcept' method.
     */
    @Test
    public void attributeProductListShouldNotExistForAdmin() throws Exception {
        mockMvc.perform(get("/admin/products"))
                .andExpect(model().attributeDoesNotExist("nonExistentAttribute"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeProductListShouldExistForAdmin() throws Exception {
        mockMvc.perform(get("/admin/products"))
                .andExpect(model().attributeExists("products"));
    }

    /**
     * FAIL
     * The performed URL instantiates a Product object named 'newProduct' with @ModelAttribute in addProduct.jsp
     * A wrong object name is given at 'andExcept' method.
     */
    @Test
    public void attributeNewProductShouldNotExist() throws Exception {
        mockMvc.perform(get("/admin/products/add"))
                .andExpect(model().attributeDoesNotExist("nonExistentAttribute"));
    }

    /**
     * PASS
     */
    @Test
    public void attributeNewProductShouldExist() throws Exception {
        mockMvc.perform(get("/admin/products/add"))
                .andExpect(model().attributeExists("newProduct"));
    }

    /**
     * PASS
     * The performed URL redirects to the same path after POST method.
     */
    @Test
    public void ingredientsAddShouldRedirectToTheSamePathAfterPostMethod() throws Exception {
        mockMvc.perform(post("/admin/products/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/products/add"));
    }

    /**
     * PASS
     * CRUD operations.
     */
    @Test
    public void crudShouldSucceedTheFourOperations() throws Exception {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("ING-1", "Banana"),
                new Ingredient("ING-2", "Papaya"),
                new Ingredient("ING-3", "Apple"),
                new Ingredient("ING-4", "Beet"),
                new Ingredient("ING-5", "Whole Milk"));

        Product p = new Product("P-000", "Mixed Fruit Shake 500mL",
                ingredients, "An amazing shake with mixed fruits",
                new BigDecimal(8), "Drink", true);

        // create
        service.save(p);
        // read
        assertThat("Mixed Fruit Shake 500mL", equalTo(service.findOne("P-000").getName()));
        // update
        p.setPrice(new BigDecimal(9));
        service.save(p);
        assertThat(new BigDecimal(9), equalTo(service.findOne("P-000").getPrice()));
        // delete
        service.delete(("P-000"));
        assertNull(service.findOne("P-000"));
    }
}
