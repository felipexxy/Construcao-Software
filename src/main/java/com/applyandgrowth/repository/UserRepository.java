package com.applyandgrowth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applyandgrowth.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
       User findByEmail(String email);
}
