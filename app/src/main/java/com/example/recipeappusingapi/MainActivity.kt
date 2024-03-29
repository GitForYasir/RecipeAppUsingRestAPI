package com.example.recipeappusingapi

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.recipeappusingapi.api.RecipeService
import com.example.recipeappusingapi.api.RetrofitHelper
import com.example.recipeappusingapi.repository.RecipeRepository
import com.example.recipeappusingapi.ui.theme.RecipeAppUsingAPITheme
import com.example.recipeappusingapi.utils.AppNavigation
import com.example.recipeappusingapi.viewModels.MainViewModel
import com.example.recipeappusingapi.viewModels.MainViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeService = RetrofitHelper.getInstance().create(RecipeService::class.java)
        val repository = RecipeRepository(recipeService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        setContent {
            RecipeAppUsingAPITheme {
                AppNavigation(mainViewModel = mainViewModel)

//                HorizontalPagerExample()
            }
        }
    }
}