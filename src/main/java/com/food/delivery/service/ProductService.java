package com.food.delivery.service;

import com.food.delivery.model.Product;

import java.util.List;

/**
 * Created by mz on 17/07/17.
 */
public interface ProductService {

    void save(Product p);

    void save(Iterable<Product> it);

    List<Product> findAll();

    Product findOne(String id);

    void delete(String id);
}
