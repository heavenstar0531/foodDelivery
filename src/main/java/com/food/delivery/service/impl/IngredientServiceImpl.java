package com.food.delivery.service.impl;

import com.food.delivery.model.Ingredient;
import com.food.delivery.model.repository.IngredientRepository;
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

    public void save(Ingredient i) {
        ingredientRepository.save(i);
    }

    public void save(Iterable<Ingredient> it) {
        ingredientRepository.save(it);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findOne(String id) {
        return ingredientRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        ingredientRepository.delete(id);
    }

}
