package com.fjfi.recipemanager.services;

import com.fjfi.recipemanager.model.Recipe;
import com.fjfi.recipemanager.persistance.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipeById(Long id) {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Recipe not found for id = " + id));
    }

    public void updateRecipe(long id, Recipe recipe) {
        Recipe recipeToUpdate = findRecipeById(id);
        recipeToUpdate.setName(recipe.getName());
        recipeToUpdate.setCategory(recipe.getCategory());
        recipeToUpdate.setDescription(recipe.getDescription());
        recipeToUpdate.setIngredients(recipe.getIngredients());
        recipeToUpdate.setDirections(recipe.getDirections());
        recipeRepository.save(recipeToUpdate);
    }


    public List<Recipe> findAll() {
        var data = recipeRepository.findAll();
        System.out.println(data);
        return (List<Recipe>) recipeRepository.findAll();
    }

    public List<Recipe> findByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
