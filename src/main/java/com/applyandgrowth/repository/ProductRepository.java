package com.applyandgrowth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.applyandgrowth.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
    Iterable<Product> findByUser_id(Long i);  
    
    Product findById (long id);
    
}

