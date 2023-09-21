package com.Recipe.RecipeApplication.domains.repositories;

import com.Recipe.RecipeApplication.domains.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
