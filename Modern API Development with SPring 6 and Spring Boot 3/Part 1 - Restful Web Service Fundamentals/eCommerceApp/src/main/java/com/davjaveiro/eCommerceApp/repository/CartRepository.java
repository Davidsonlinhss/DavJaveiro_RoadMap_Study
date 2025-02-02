package com.davjaveiro.eCommerceApp.repository;

import com.davjaveiro.eCommerceApp.entity.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<CartEntity, UUID> {
    @Query("select c from CartEntity c join c.user u where u.id = :customerId")
    public Optional<CartEntity> findByCustomerId(
            @Param("customerId")
            UUID customerId);
}
