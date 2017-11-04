package com.food.delivery.model.repository.mysql;

import com.food.delivery.model.Product;

import java.util.List;

public interface ProductRepository {

    void create(Product p);

    Product read(String id);

    void update(Product p);

    void delete(String id);

    List<Product> readAll();
}
