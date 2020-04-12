package com.hgsachin.springrecipedemo.controllers;

import com.hgsachin.springrecipedemo.commands.RecipeCommand;
import com.hgsachin.springrecipedemo.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showRecipeDetails(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeDetails(Long.parseLong(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.parseLong(id)));
        return "recipe/recipeForm";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String addOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
}
