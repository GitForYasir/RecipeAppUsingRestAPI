package com.example.recipeappusingapi.repository

import com.example.recipeappusingapi.api.RecipeService
import com.example.recipeappusingapi.models.RecipeList

class RecipeRepository(private val recipeService: RecipeService) {
    suspend fun getRecipe(): RecipeList {
        val result = recipeService.getRecipes()
        return result.body()!!
    }
}