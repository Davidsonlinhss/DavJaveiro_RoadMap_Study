package com.davjaveiro.helloworldjpa.springsecuritytest.repository;

import com.davjaveiro.helloworldjpa.springsecuritytest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
 }
