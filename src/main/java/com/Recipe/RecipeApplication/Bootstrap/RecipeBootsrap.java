package com.Recipe.RecipeApplication.Bootstrap;

import com.Recipe.RecipeApplication.domains.*;
import com.Recipe.RecipeApplication.domains.repositories.CategoryRepository;
import com.Recipe.RecipeApplication.domains.repositories.RecipeRepository;
import com.Recipe.RecipeApplication.domains.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootsrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootsrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> teaspoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");
        if (!teaspoonUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");
        Optional<UnitOfMeasure> pintUOM = unitOfMeasureRepository.findByUnitOfMeasure("Pint");
        if(!pintUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");
        Optional<UnitOfMeasure> tablespoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon");
        if(!tablespoonUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");
        Optional<UnitOfMeasure> cupUOM = unitOfMeasureRepository.findByUnitOfMeasure("Cup");
        if(!cupUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");
        Optional<UnitOfMeasure> ounceUOM = unitOfMeasureRepository.findByUnitOfMeasure("Ounce");
        if(!ounceUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");
        Optional<UnitOfMeasure> eachUOM = unitOfMeasureRepository.findByUnitOfMeasure("Each");
        if(!eachUOM.isPresent())
            throw new RuntimeException("This UOM doesn't exist");


        UnitOfMeasure teaspoon =teaspoonUOM.get();
        UnitOfMeasure pint = pintUOM.get();
        UnitOfMeasure tablespoon = tablespoonUOM.get();
        UnitOfMeasure cup = cupUOM.get();
        UnitOfMeasure ounce = ounceUOM.get();
        UnitOfMeasure each = eachUOM.get();


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent())
        {
            throw new RuntimeException("This category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent())
        {
            throw new RuntimeException("This category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setCookTime("0");
        guacamole.setPrepTime("10");
        guacamole.setServings("3");
        guacamole.setSource("30");
        guacamole.setUrl("https://www.makesomething.com");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacaNotes = new Notes();
        guacaNotes.setRecipeNotes("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.");

        guacaNotes.setRecipe(guacamole);
        guacamole.setNotes(guacaNotes);

        guacamole.getIngredients().add(new Ingredient("Ripe Avocados", new BigDecimal(2), each, guacamole));
        guacamole.getIngredients().add(new Ingredient("Kosher Salt", new BigDecimal(0.25), teaspoon, guacamole));
        guacamole.getIngredients().add(new Ingredient("Fresh Lime", new BigDecimal(1), tablespoon, guacamole));
        guacamole.getIngredients().add(new Ingredient("Chillis", new BigDecimal(2), each, guacamole));
        guacamole.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tablespoon, guacamole));
        guacamole.getIngredients().add(new Ingredient("Tomato", new BigDecimal(0.5), each, guacamole));

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);


        return recipes;
    }


}
