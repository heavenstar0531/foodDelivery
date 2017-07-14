package com.food.delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mz on 14/07/17.
 */

@Document
public class Ingredient {

    @Id
    private String id;
    private String name;

    public Ingredient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
