package com.davjaveiro.eCommerceApp.repository;

import com.davjaveiro.eCommerceApp.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

// OrdeerRepository herda os métodos de CruRepository, pois extende a interface...
public interface OrderRepository extends CrudRepository<OrderEntity, UUID>, OrderRepositoryExt {
    @Query("Select o from OrderEntity o join o.userEntity u where u.id = :customerId")
    public Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
