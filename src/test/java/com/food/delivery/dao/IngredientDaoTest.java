package com.food.delivery.dao;

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
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@Transactional()
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
public class IngredientDaoTest {

    @Autowired
    private IngredientService ingredientService;

    /**
     * PASS
     * CRUD operations.
     *
     */
    @Test
    public void crudShouldSucceedTheFourOperations() throws Exception {
        Ingredient i = new Ingredient("ING-99", "Apple");

        // create
        ingredientService.create(i);
        // read
        assertThat("Apple", equalTo(ingredientService.read("ING-99").getName()));
        // update
        i.setName("Green Apple");
        ingredientService.update(i);

        // To update with a new product will cause NonUniqueObjectException:
        // A different object with the same identifier value was already associated with the session
        // service.update(new Ingredient("ING-99", "Green Apple"));

        assertThat("Green Apple", equalTo(ingredientService.read("ING-99").getName()));
        // delete
        ingredientService.delete(("ING-99"));
        assertNull(ingredientService.read("ING-99"));
    }

}