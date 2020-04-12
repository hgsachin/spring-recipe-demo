package com.hgsachin.springrecipedemo.commands;

import com.hgsachin.springrecipedemo.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private Integer cookTime;
    private Integer prepTime;
    private Set<CategoryCommand> categories = new HashSet<>();
    private String description;
    private Difficulty difficulty;
    private String directions;
    private Byte[] image;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private NotesCommand notes;
    private Integer servings;
    private String source;
    private String url;
}
