package com.davjaveiro.eCommerceApp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;


@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue
    @Column (name = "ID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;

    @Column(name = "UNIT_PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<CartEntity> cart;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;




}
