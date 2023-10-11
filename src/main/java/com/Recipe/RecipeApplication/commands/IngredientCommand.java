package com.Recipe.RecipeApplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
    private String description;
}
