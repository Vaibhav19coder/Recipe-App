package com.Recipe.RecipeApplication.services;

import com.Recipe.RecipeApplication.domains.Recipe;
import com.Recipe.RecipeApplication.domains.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}
