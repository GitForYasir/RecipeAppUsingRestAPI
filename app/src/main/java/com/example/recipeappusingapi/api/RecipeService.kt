package com.example.recipeappusingapi.api

import com.example.recipeappusingapi.models.RecipeList
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): Response<RecipeList>
}