package com.food.delivery.model;

import javax.persistence.*;

/**
 * Created by mz on 14/07/17.
 * <p>
 * One or more ingredients are part of a food.
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    private String id;
    @Column(length = 50)
    private String name;

    public Ingredient() {

    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
