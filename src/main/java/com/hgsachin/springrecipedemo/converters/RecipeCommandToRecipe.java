package com.hgsachin.springrecipedemo.converters;

import com.hgsachin.springrecipedemo.commands.RecipeCommand;
import com.hgsachin.springrecipedemo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory commandToCategory;
    private final NotesCommandToNotes commandToNotes;
    private final IngredientCommandToIngredient commandToIngredient;

    public RecipeCommandToRecipe(CategoryCommandToCategory commandToCategory,
                                 NotesCommandToNotes commandToNotes,
                                 IngredientCommandToIngredient commandToIngredient) {
        this.commandToCategory = commandToCategory;
        this.commandToNotes = commandToNotes;
        this.commandToIngredient = commandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (null == recipeCommand) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setSource(recipeCommand.getSource());
        recipe.setNotes(commandToNotes.convert(recipeCommand.getNotes()));
        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories()
                    .forEach(category -> recipe.getCategories().add(commandToCategory.convert(category)));
        }
        if (null !=  recipeCommand.getIngredients() && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(commandToIngredient.convert(ingredient)));
        }
        recipe.setImage(recipeCommand.getImage());
        return recipe;
    }
}
