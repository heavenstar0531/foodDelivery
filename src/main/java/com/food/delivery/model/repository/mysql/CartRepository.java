package com.food.delivery.model.repository.mysql;

import com.food.delivery.dto.CartDto;

public interface CartRepository {

    void create(CartDto c);

    void update(CartDto c);

    CartDto read(String id);

    void delete(String id);
}
