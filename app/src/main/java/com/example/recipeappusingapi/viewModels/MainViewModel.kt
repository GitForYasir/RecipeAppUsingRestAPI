package com.example.recipeappusingapi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappusingapi.models.RecipeList
import com.example.recipeappusingapi.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableLiveData<RecipeList>()
    val recipes: LiveData<RecipeList> get() = _recipes

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRecipe()
            _recipes.postValue(result)
        }
    }
}