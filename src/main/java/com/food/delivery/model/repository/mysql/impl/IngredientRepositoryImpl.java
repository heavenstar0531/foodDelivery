package com.food.delivery.model.repository.mysql.impl;

import com.food.delivery.model.Ingredient;
import com.food.delivery.model.repository.mysql.IngredientRepository;
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
public class IngredientRepositoryImpl implements IngredientRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public IngredientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void create(Ingredient i) {
        sessionFactory.getCurrentSession().save(i);
    }

    @Transactional
    @Override
    public Ingredient read(String id) {
        return sessionFactory.getCurrentSession().get(Ingredient.class, id);
    }

    @Transactional
    @Override
    public void update(Ingredient i) {
        sessionFactory.getCurrentSession().update(i);
    }

    @Transactional
    @Override
    public void delete(String id) {
        Ingredient i = sessionFactory.getCurrentSession().get(Ingredient.class, id);
        sessionFactory.getCurrentSession().delete(i);
    }

    @Transactional
    @Override
    public List<Ingredient> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
        Root<Ingredient> ingredientRoot = criteria.from(Ingredient.class);
        criteria.select(ingredientRoot);
        // Fetch all ingredients
        return session.createQuery(criteria).getResultList();
    }
}
