package com.Recipe.RecipeApplication.domains.repositories;

import com.Recipe.RecipeApplication.domains.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfMeasure);

}
