package com.applyandgrowth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.applyandgrowth.models.Exercise;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Iterable<Exercise> findByUser_id(Long i);  
    
    Exercise findById (int id);
}
