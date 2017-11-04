package com.food.delivery.service.impl;

import com.food.delivery.model.Product;
import com.food.delivery.model.repository.mysql.ProductRepository;
import com.food.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mz on 17/07/17.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product p) {
        productRepository.create(p);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.readAll();
    }

    @Override
    public void update(Product p) {
        productRepository.update(p);
    }

    @Override
    public Product read(String id) {
        return productRepository.read(id);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
}
