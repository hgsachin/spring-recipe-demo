package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.commands.RecipeCommand;
import com.hgsachin.springrecipedemo.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeDetails(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
