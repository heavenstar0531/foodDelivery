package com.food.delivery.model.repository.mysql.impl;

import com.food.delivery.dto.CartDto;
import com.food.delivery.model.repository.mysql.CartRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartRepositoryImpl implements CartRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(CartDto c) {
        sessionFactory.getCurrentSession().save(c);
    }

    @Override
    public void update(CartDto c) {
        sessionFactory.getCurrentSession().update(c);
    }

    @Override
    public CartDto read(String id) {
        return sessionFactory.getCurrentSession().get(CartDto.class, id);
    }

    @Override
    public void delete(String id) {
        CartDto cartDto = sessionFactory.getCurrentSession().get(CartDto.class, id);
        sessionFactory.getCurrentSession().delete(cartDto);
    }

}
