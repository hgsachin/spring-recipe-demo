package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand getIngredientByRecipeNIngredientIds(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
