package com.davjaveiro.eCommerceApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID  id;



    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;


    public UUID getId() {
        return id;
    }

    public TagEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagEntity setName(String name) {
        this.name = name;
        return this;
    }
}

