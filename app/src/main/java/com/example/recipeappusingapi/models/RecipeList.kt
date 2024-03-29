package com.example.recipeappusingapi.models

data class RecipeList(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)