package com.hgsachin.springrecipedemo.converters;

import com.hgsachin.springrecipedemo.commands.RecipeCommand;
import com.hgsachin.springrecipedemo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand toCategoryCommand;
    private final NotesToNotesCommand toNotesCommand;
    private final IngredientToIngredientCommand toIngredientCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand toCategoryCommand,
                                 NotesToNotesCommand toNotesCommand,
                                 IngredientToIngredientCommand toIngredientCommand) {
        this.toCategoryCommand = toCategoryCommand;
        this.toNotesCommand = toNotesCommand;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (null == recipe) {
            return null;
        }
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setNotes(toNotesCommand.convert(recipe.getNotes()));
        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories()
                    .forEach(category -> recipeCommand.getCategories()
                            .add(toCategoryCommand.convert(category)));
        }
        if (null !=  recipe.getIngredients() && recipe.getIngredients().size() > 0) {
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients()
                            .add(toIngredientCommand.convert(ingredient)));
        }
        recipeCommand.setImage(recipe.getImage());

        return recipeCommand;
    }
}
