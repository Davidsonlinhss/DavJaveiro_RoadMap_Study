package com.davjaveiro.eCommerceApp.repository;

import com.davjaveiro.eCommerceApp.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOder m);
}
