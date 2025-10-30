package com.mcon152.recipeshare.repository;

import com.mcon152.recipeshare.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // repository methods (default CRUD provided by JpaRepository)
}

