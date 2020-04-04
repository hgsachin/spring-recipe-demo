package com.hgsachin.springrecipedemo.repositories;

import com.hgsachin.springrecipedemo.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
