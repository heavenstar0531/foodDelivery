package com.food.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mz on 17/07/17.
 */
@XmlRootElement
@Entity
@Table(name = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", targetEntity = Ingredient.class)
    @Transient
    private List<Ingredient> ingredients;
    private String description;
    private BigDecimal price;
    private boolean active;
    @JsonIgnore
    @Transient
    private MultipartFile image;

    public Product() {
        // Empty constructor for persistence framework
    }

    public Product(String id, String name, List<Ingredient> ingredients, String description, BigDecimal price, String category, boolean active) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.price = price;
        this.category = category;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@OneToMany
    @JoinTable(
            name="ingredients",
            joinColumns = @JoinColumn( name="product"),
            inverseJoinColumns = @JoinColumn( name="ingredient")
    )*/
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
