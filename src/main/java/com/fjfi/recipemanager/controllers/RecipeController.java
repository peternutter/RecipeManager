package com.fjfi.recipemanager.controllers;

import com.fjfi.recipemanager.model.Recipe;
import com.fjfi.recipemanager.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
//@AllArgsConstructor
//@NoArgsConstructor
@Validated
public class RecipeController {
    RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.findAll();
    }

    @PostMapping("/recipes/new")
    public ResponseEntity<Object> postRecipe(@Valid @RequestBody Recipe recipe) {

        Recipe newRecipe = recipeService.save(recipe);
        return ResponseEntity.ok(Map.of("id", newRecipe.getId()));
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable("id") @Min(1) long id) {
        return recipeService.findRecipeById(id);


    }

    @DeleteMapping("/recipes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
    }

    @PutMapping("/recipes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }

    @GetMapping("/recipes/search")
    public List<Recipe> searchRecipes(@RequestParam HashMap<String, String> params) {
        if (params.size() != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid number of search parameters");
        }

        if (params.containsKey("name")) {
            return recipeService.findByName(params.get("name"));
        } else if (params.containsKey("category")) {
            return recipeService.findByCategory(params.get("category"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid search parameter");
        }

    }

}
