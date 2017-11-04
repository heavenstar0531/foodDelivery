package com.food.delivery.model.repository.mysql.impl;

import com.food.delivery.model.Product;
import com.food.delivery.model.repository.mysql.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void create(Product p) {
        sessionFactory.getCurrentSession().save(p);
    }

    @Transactional
    @Override
    public Product read(String id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Transactional
    @Override
    public void update(Product p) {
        sessionFactory.getCurrentSession().update(p);
    }

    @Transactional
    @Override
    public void delete(String id) {
        Product p = sessionFactory.getCurrentSession().get(Product.class, id);
        sessionFactory.getCurrentSession().delete(p);
    }

    @Transactional
    @Override
    public List<Product> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        criteria.select(productRoot);
        // Fetch all ingredients
        return session.createQuery(criteria).getResultList();
    }
}
