package com.davjaveiro.eCommerceApp.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @OneToMany
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private List<UserEntity> users;
}
