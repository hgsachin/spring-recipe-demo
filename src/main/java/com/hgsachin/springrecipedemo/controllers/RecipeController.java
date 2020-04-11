package com.hgsachin.springrecipedemo.controllers;

import com.hgsachin.springrecipedemo.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showRecipeDetails(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeDetails(new Long(id)));
        return  "recipe/show";
    }
}
