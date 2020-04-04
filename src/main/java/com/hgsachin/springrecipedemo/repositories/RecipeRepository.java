package com.hgsachin.springrecipedemo.repositories;

import com.hgsachin.springrecipedemo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
