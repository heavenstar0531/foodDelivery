package com.food.delivery.model.repository.mysql;

import com.food.delivery.model.Ingredient;

import java.util.List;

public interface IngredientRepository {

    void create(Ingredient i);

    Ingredient read(String id);

    void update(Ingredient i);

    void delete(String id);

    List<Ingredient> readAll();

}
