package com.food.delivery.controller;

import com.food.delivery.model.Ingredient;
import com.food.delivery.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mz on 14/07/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final IngredientService ingredientService;

    @Autowired
    public HomeController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping
    public String home(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("I1", "Banana"),
                new Ingredient("I2", "Papaya"),
                new Ingredient("I3", "Apple"),
                new Ingredient("I4", "Pineapple"),
                new Ingredient("I5", "Granola"),
                new Ingredient("I6", "Cereal Four")
        );

        ingredientService.save(ingredients);
        List<Ingredient> ingredientsFromMongo = ingredientService.readAll();

        model.addAttribute("message1", "This is a message from HomeController. See data from MongoDB below:");
        model.addAttribute("message2", ingredientsFromMongo.toString());
        return "home";
    }

}
