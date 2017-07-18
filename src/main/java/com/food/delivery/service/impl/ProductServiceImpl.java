package com.food.delivery.service.impl;

import com.food.delivery.model.Product;
import com.food.delivery.model.repository.ProductRepository;
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
    public void save(Product p) {
        productRepository.save(p);
    }

    @Override
    public void save(Iterable<Product> it) {
        productRepository.save(it);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
}
