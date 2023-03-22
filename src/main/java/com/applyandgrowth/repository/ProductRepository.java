package com.applyandgrowth.repository;

import org.springframework.data.repository.CrudRepository;

import com.applyandgrowth.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
