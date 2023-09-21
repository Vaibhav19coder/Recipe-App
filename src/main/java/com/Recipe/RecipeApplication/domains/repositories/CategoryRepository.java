package com.Recipe.RecipeApplication.domains.repositories;

import com.Recipe.RecipeApplication.domains.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}
