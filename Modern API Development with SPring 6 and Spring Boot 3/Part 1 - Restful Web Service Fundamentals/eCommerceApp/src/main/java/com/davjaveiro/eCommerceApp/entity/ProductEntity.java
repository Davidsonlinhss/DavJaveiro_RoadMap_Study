package com.davjaveiro.eCommerceApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCT_TAG",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private List<TagEntity> tags = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ItemEntity> items;

    public ProductEntity(UUID id, @NotNull(message = "Product name is required.") String name, String description, BigDecimal price, int count, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.count = count;
        this.imageURL = imageURL;
    }

    public ProductEntity() {}

    public UUID getId() {
        return id;
    }

    public ProductEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public @NotNull(message = "Product name is required.") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Product name is required.") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
