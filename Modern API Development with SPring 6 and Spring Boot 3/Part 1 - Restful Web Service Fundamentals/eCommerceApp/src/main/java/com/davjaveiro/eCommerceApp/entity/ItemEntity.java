package com.davjaveiro.eCommerceApp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CartEntity> getCart() {
        return cart;
    }

    public void setCart(List<CartEntity> cart) {
        this.cart = cart;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return quantity == that.quantity && Objects.equals(product, that.product) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price, quantity);
    }
}
