package com.hgsachin.springrecipedemo.controllers;

import com.hgsachin.springrecipedemo.commands.IngredientCommand;
import com.hgsachin.springrecipedemo.commands.RecipeCommand;
import com.hgsachin.springrecipedemo.commands.UnitOfMeasureCommand;
import com.hgsachin.springrecipedemo.services.IngredientService;
import com.hgsachin.springrecipedemo.services.RecipeService;
import com.hgsachin.springrecipedemo.services.UOMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UOMService uomService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UOMService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(Model model, @PathVariable String recipeId) {
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.parseLong(recipeId)));
        return "ingredients/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId,
                                 Model model) {
        model.addAttribute("ingredient",
                ingredientService.getIngredientByRecipeNIngredientIds(
                        Long.parseLong(recipeId),
                        Long.parseLong(ingredientId)));
        return "ingredients/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredientForm(@PathVariable String recipeId,
                                   @PathVariable String ingredientId,
                                   Model model) {
        model.addAttribute("ingredient",
                ingredientService.getIngredientByRecipeNIngredientIds(
                        Long.parseLong(recipeId),
                        Long.parseLong(ingredientId)));
        model.addAttribute("uomList", uomService.getAllUOMs());
        return "ingredients/ingredientForm";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String updateIngredient(Model model, @ModelAttribute IngredientCommand ingredient) {
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(ingredient);
        return "redirect:/recipe/"
                + ingredientCommand.getRecipeId()
                + "/ingredients";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String saveNewIngredient(Model model, @PathVariable String recipeId) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(Long.parseLong(recipeId));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", uomService.getAllUOMs());
        return "ingredients/ingredientForm";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
        ingredientService.deleteIngredientByRecipeNIngredientIds(Long.parseLong(recipeId), Long.parseLong(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
