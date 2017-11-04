package com.food.delivery.service.impl;

import com.food.delivery.model.Ingredient;
import com.food.delivery.model.repository.mysql.IngredientRepository;
import com.food.delivery.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mz on 14/07/17.
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void create(Ingredient i) {
        ingredientRepository.create(i);
    }

    @Override
    public Ingredient read(String id) {
        return ingredientRepository.read(id);
    }

    @Override
    public void update(Ingredient i) {
        ingredientRepository.update(i);
    }

    @Override
    public void delete(String id) {
        ingredientRepository.delete(id);
    }

    public List<Ingredient> readAll() {
        return ingredientRepository.readAll();
    }

}
