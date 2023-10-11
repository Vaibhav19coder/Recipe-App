package com.Recipe.RecipeApplication.converters;

import com.Recipe.RecipeApplication.commands.CategoryCommand;
import com.Recipe.RecipeApplication.commands.IngredientCommand;
import com.Recipe.RecipeApplication.commands.RecipeCommand;
import com.Recipe.RecipeApplication.domains.Category;
import com.Recipe.RecipeApplication.domains.Ingredient;
import com.Recipe.RecipeApplication.domains.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory, NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source==null)
            return null;
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setServings(source.getServings());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        recipe.setDirections(source.getDirections());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        if(source.getCategories()!=null && source.getCategories().size()>0)
        {
            source.getCategories().forEach((CategoryCommand categoryCommand)->recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
        }
        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients().forEach((IngredientCommand ingredientCommand)->recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
        }
        return recipe;
    }
}
