package com.mcon152.recipeshare.service;

import com.mcon152.recipeshare.Recipe;
import com.mcon152.recipeshare.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repo;

    public RecipeServiceImpl(RecipeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipe.setId(null); // ensure new entity
        return repo.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return repo.findAll();
    }

    @Override
    public Optional<Recipe> getRecipeById(long id) {
        return repo.findById(id);
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Recipe> updateRecipe(long id, Recipe updatedRecipe) {
        return repo.findById(id).map(existing -> {
            // Preserve entity type (do not replace the DB row with a different subtype)
            existing.setTitle(updatedRecipe.getTitle());
            existing.setDescription(updatedRecipe.getDescription());
            existing.setIngredients(updatedRecipe.getIngredients());
            existing.setInstructions(updatedRecipe.getInstructions());
            existing.setServings(updatedRecipe.getServings());
            return repo.save(existing);
        });
    }

    @Override
    public Optional<Recipe> patchRecipe(long id, Recipe partialRecipe) {
        return repo.findById(id).map(existing -> {
            if (partialRecipe.getTitle() != null) existing.setTitle(partialRecipe.getTitle());
            if (partialRecipe.getDescription() != null) existing.setDescription(partialRecipe.getDescription());
            if (partialRecipe.getIngredients() != null) existing.setIngredients(partialRecipe.getIngredients());
            if (partialRecipe.getInstructions() != null) existing.setInstructions(partialRecipe.getInstructions());
            if (partialRecipe.getServings() != null) existing.setServings(partialRecipe.getServings());
            return repo.save(existing);
        });
    }
}
