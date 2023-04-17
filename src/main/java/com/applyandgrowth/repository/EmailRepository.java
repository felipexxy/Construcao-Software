package com.applyandgrowth.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.applyandgrowth.models.Email;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}