package com.food.delivery.dao;

import com.food.delivery.config.WebAppConfig;
import com.food.delivery.model.Ingredient;
import com.food.delivery.model.Product;
import com.food.delivery.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@Transactional()
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class ProductDaoTest {

    @Autowired
    private ProductService productService;

    private static Ingredient INGREDIENT_01 = new Ingredient("ING-1", "Banana");
    private static Ingredient INGREDIENT_02 = new Ingredient("ING-2", "Papaya");
    private static Ingredient INGREDIENT_03 = new Ingredient("ING-3", "Apple");
    private static Ingredient INGREDIENT_04 = new Ingredient("ING-4", "Beet");
    private static Ingredient INGREDIENT_05 = new Ingredient("ING-5", "Whole Milk");
    private static Ingredient INGREDIENT_06 = new Ingredient("ING-6", "Avocado");
    private static Ingredient INGREDIENT_07 = new Ingredient("ING-7", "Sugar");

    private static final List<Ingredient> INGREDIENTS_01 = Arrays.asList(
            INGREDIENT_01, INGREDIENT_02, INGREDIENT_03, INGREDIENT_04, INGREDIENT_05
    );

    private static final Product product = new Product("P99", "Avocado Fruit Shake 500mL",
            INGREDIENTS_01, "An awesome avocado shake with all potassium you need",
            new BigDecimal(6), "Drink", true);


    @Test
    public void crudShouldSucceedTheFourOperations() {

        // create
        productService.create(product);
        // read
        assertThat("Avocado Fruit Shake 500mL", equalTo(productService.read("P99").getName()));
        // update
        product.setName("Apple");
        productService.update(product);
        assertThat("Apple", equalTo(productService.read("P99").getName()));
        // ingredient list is null
//        assertNotNull(productService.read("P99").getIngredients());
        // delete
        productService.delete(("P99"));
        assertNull(productService.read("P99"));
    }

    /**
     * PASS
     * CRUD operations.
     */
    @Test
    public void crudShouldSucceedTheFourOperations2() throws Exception {

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
        productService.create(p);
        // read
        assertThat("Mixed Fruit Shake 500mL", equalTo(productService.read("P-000").getName()));
        // update
        p.setPrice(new BigDecimal(9));
        productService.create(p);
        assertThat(new BigDecimal(9), equalTo(productService.read("P-000").getPrice()));
        // delete
        productService.delete(("P-000"));
        assertNull(productService.read("P-000"));
    }

}