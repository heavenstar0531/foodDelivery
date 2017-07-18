package com.food.delivery.service;

import com.food.delivery.model.Ingredient;

import java.util.List;

/**
 * Created by mz on 14/07/17.
 */
public interface IngredientService {

    void save(Ingredient i);

    void save(Iterable<Ingredient> it);

    List<Ingredient> findAll();

    Ingredient findOne(String id);

    void delete(String id);
}
