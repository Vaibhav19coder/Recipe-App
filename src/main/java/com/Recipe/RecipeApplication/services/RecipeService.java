package com.Recipe.RecipeApplication.services;

import com.Recipe.RecipeApplication.domains.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(long id);
}
