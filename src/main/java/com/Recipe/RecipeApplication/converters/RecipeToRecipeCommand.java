package com.Recipe.RecipeApplication.converters;


import com.Recipe.RecipeApplication.commands.RecipeCommand;
import com.Recipe.RecipeApplication.domains.Category;
import com.Recipe.RecipeApplication.domains.Ingredient;
import com.Recipe.RecipeApplication.domains.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categorytoCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categorytoCategoryCommand, NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categorytoCategoryCommand = categorytoCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source==null)
            return null;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        if(source.getCategories()!=null && source.getCategories().size()>0)
        {
            source.getCategories().forEach((Category category)->recipeCommand.getCategories().add(categorytoCategoryCommand.convert(category)));
        }
        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients().forEach((Ingredient ingredient)->recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
        }
        return recipeCommand;
    }

}
