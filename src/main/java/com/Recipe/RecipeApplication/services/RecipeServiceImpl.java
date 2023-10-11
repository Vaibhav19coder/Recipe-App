package com.Recipe.RecipeApplication.services;

import com.Recipe.RecipeApplication.commands.RecipeCommand;
import com.Recipe.RecipeApplication.converters.CategoryCommandToCategory;
import com.Recipe.RecipeApplication.converters.RecipeCommandToRecipe;
import com.Recipe.RecipeApplication.converters.RecipeToRecipeCommand;
import com.Recipe.RecipeApplication.domains.Recipe;
import com.Recipe.RecipeApplication.domains.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes=new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipes :: add);
        return recipes;
    }
    @Override
    public Recipe findById(long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
