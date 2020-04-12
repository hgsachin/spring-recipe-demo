package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.commands.IngredientCommand;
import com.hgsachin.springrecipedemo.converters.IngredientCommandToIngredient;
import com.hgsachin.springrecipedemo.converters.IngredientToIngredientCommand;
import com.hgsachin.springrecipedemo.domain.Ingredient;
import com.hgsachin.springrecipedemo.domain.Recipe;
import com.hgsachin.springrecipedemo.repositories.RecipeRepository;
import com.hgsachin.springrecipedemo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final IngredientCommandToIngredient toIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand toIngredientCommand,
                                 IngredientCommandToIngredient toIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.toIngredientCommand = toIngredientCommand;
        this.toIngredient = toIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand getIngredientByRecipeNIngredientIds(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (!recipe.isPresent()) {
            log.error("Recipe with ID " + recipeId + " not found");
            return null;
        }
        Optional<IngredientCommand> ingredientCommandOpt = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(toIngredientCommand::convert)
                .findFirst();
        if (!ingredientCommandOpt.isPresent()) {
            log.error("Ingredient with ID " + ingredientId + " not found");
            return null;
        } else return ingredientCommandOpt.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
        if (!optionalRecipe.isPresent()) {
            log.error("No Recipe Found");
            return new IngredientCommand();
        } else {
            Recipe recipe = optionalRecipe.get();
            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if (!optionalIngredient.isPresent()) {
                log.error("pppp No Ingredient Found");
                recipe.addIngredients(toIngredient.convert(ingredientCommand));
            } else {
                Ingredient foundIngredient = optionalIngredient.get();
                foundIngredient.setUnitOfMeasure(
                        unitOfMeasureRepository.findById(
                                ingredientCommand.getUnitOfMeasure().getId())
                                .orElseThrow(() -> new RuntimeException("No UOM found")));
                foundIngredient.setDescription(ingredientCommand.getDescription());
                foundIngredient.setAmount(ingredientCommand.getAmount());
            }
            Recipe savedRecipe = recipeRepository.save(recipe);
            return toIngredientCommand.convert(
                    savedRecipe.getIngredients().stream()
                            .filter(ingredient -> ingredientCommand.getId().equals(ingredient.getId()))
                            .findFirst().get());
        }
    }
}
