package com.fjfi.recipemanager.persistance;

import com.fjfi.recipemanager.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
