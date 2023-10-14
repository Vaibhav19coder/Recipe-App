package com.Recipe.RecipeApplication.controllers;

import com.Recipe.RecipeApplication.commands.RecipeCommand;
import com.Recipe.RecipeApplication.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));

        return "/recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe/")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand)
    {
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+saveCommand.getId();
    }


}
