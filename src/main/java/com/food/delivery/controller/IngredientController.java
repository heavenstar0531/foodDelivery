package com.food.delivery.controller;

import com.food.delivery.config.RootConfig;
import com.food.delivery.model.Ingredient;
import com.food.delivery.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mz on 15/07/17.
 */
@Controller
@RequestMapping("admin/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;

        // temporary data for manual testing purpose
//        ingredientService.create(RootConfig.INGREDIENTS_01);
//        ingredientService.create(RootConfig.INGREDIENTS_02);
        RootConfig.INGREDIENTS_01.forEach(ingredientService::create);
    }

    @RequestMapping
    public String readAll(Model model) {
        List<Ingredient> ingredientsFromMongo = ingredientService.readAll();
        model.addAttribute("ingredients", ingredientsFromMongo);
        return "adminIngredients";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNew(Model model) {
        Ingredient i = new Ingredient();
        model.addAttribute("newIngredient", i);
        return "addIngredient";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNew(@ModelAttribute Ingredient newIngredient) {
        ingredientService.create(newIngredient);
        return "redirect:/admin/ingredients/add";
    }
}
