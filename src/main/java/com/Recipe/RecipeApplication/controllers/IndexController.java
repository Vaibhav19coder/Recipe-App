package com.Recipe.RecipeApplication.controllers;

import com.Recipe.RecipeApplication.domains.Category;
import com.Recipe.RecipeApplication.domains.UnitOfMeasure;
import com.Recipe.RecipeApplication.domains.repositories.CategoryRepository;
import com.Recipe.RecipeApplication.domains.repositories.UnitOfMeasureRepository;
import com.Recipe.RecipeApplication.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Component
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getHome(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
