package com.example.recipeappusingapi.utils

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeappusingapi.viewModels.MainViewModel

@Composable
fun AppNavigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "homeScreen"){
        composable("homeScreen"){
            HomeScreen(mainViewModel, navController)
        }
        composable("detailScreen/{recipeId}"){backStackEntry->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            val singleRecipe = mainViewModel.recipes.value?.recipes?.find { it.id.toString() == recipeId }
            singleRecipe?.let {
                DetailScreen(navController, singleRecipe, mainViewModel)
            }
        }
    }
}