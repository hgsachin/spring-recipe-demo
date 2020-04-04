package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
