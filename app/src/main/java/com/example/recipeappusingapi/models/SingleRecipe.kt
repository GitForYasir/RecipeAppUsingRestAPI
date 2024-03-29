package com.example.recipeappusingapi.models

data class SingleRecipe(
    val id: Int,
    val image: String,
    val instructions: List<String>,
    val ingredients: List<String>,
    val name: String
)