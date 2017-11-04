package com.food.delivery.config;

import com.food.delivery.model.Ingredient;
import com.food.delivery.model.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mz on 14/07/17.
 */
@Configuration
@ComponentScan("com.food.delivery")
public class RootConfig {

    /**
     * These static data are used only for testing the application manually.
     * For JUnit tests see src/test
     */

    private static Ingredient INGREDIENT_01 = new Ingredient("ING-1", "Banana");
    private static Ingredient INGREDIENT_02 = new Ingredient("ING-2", "Papaya");
    private static Ingredient INGREDIENT_03 = new Ingredient("ING-3", "Apple");
    private static Ingredient INGREDIENT_04 = new Ingredient("ING-4", "Beet");
    private static Ingredient INGREDIENT_05 = new Ingredient("ING-5", "Whole Milk");
    private static Ingredient INGREDIENT_06 = new Ingredient("ING-6", "Avocado");
    private static Ingredient INGREDIENT_07 = new Ingredient("ING-7", "Sugar");

    public static final List<Ingredient> INGREDIENTS_01 = Arrays.asList(
            INGREDIENT_01, INGREDIENT_02, INGREDIENT_03, INGREDIENT_04, INGREDIENT_05
    );

    public static final List<Ingredient> INGREDIENTS_02 = Arrays.asList(
            INGREDIENT_06, INGREDIENT_05, INGREDIENT_07);

    public static final Product PRODUCT_01 = new Product("P1", "Avocado Fruit Shake 500mL",
            INGREDIENTS_01, "An awesome avocado shake with all potassium you need",
            new BigDecimal(6), "Drink", true);

    public static final Product PRODUCT_02 = new Product("P2", "Mixed Fruit Shake 500mL",
            INGREDIENTS_02, "An amazing shake with mixed fruits",
            new BigDecimal(8), "Drink", true);

    public static final Product PRODUCT_03 = new Product("P0", "ShouldNotAppearProduct",
            INGREDIENTS_02, "This product should not be visible for users because it is not active",
            new BigDecimal(8), "Drink", false);
}
