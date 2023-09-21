package com.Recipe.RecipeApplication.controllers;

import com.Recipe.RecipeApplication.domains.Category;
import com.Recipe.RecipeApplication.domains.UnitOfMeasure;
import com.Recipe.RecipeApplication.domains.repositories.CategoryRepository;
import com.Recipe.RecipeApplication.domains.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Component
public class IndexController {

    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getHome(){

        Optional<Category> categoryOptional=categoryRepository.findByDescription("America");

        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByUnitOfMeasure("Cup");

        System.out.println("Category ID is "+categoryOptional.get().getId());
        System.out.println("UOM ID is "+unitOfMeasureOptional.get().getId());

        return "index";
    }

}
