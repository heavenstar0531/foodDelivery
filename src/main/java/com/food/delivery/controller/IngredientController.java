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
        ingredientService.save(RootConfig.INGREDIENTS_01);
        ingredientService.save(RootConfig.INGREDIENTS_02);
    }

    @RequestMapping
    public String readAll(Model model) {
        List<Ingredient> ingredientsFromMongo = ingredientService.findAll();
        model.addAttribute("ingredients", ingredientsFromMongo);
        return "ingredients";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNew(Model model) {
        Ingredient i = new Ingredient();
        model.addAttribute("newIngredient", i);
        return "addIngredient";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNew(@ModelAttribute Ingredient newIngredient) {
        ingredientService.save(newIngredient);
        return "redirect:/admin/ingredients/add";
    }
}
