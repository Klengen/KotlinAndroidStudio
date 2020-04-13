package com.example.klengen.kotlinandroidtry.database.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.klengen.kotlinandroidtry.database.*
import kotlinx.coroutines.launch

class RecipeViewModel (application: Application):AndroidViewModel(application){

    private val repository: RecipeRepository

    val allRecipesWithIngredients: LiveData<List<RecipeWithIngredients>>
    val allRecipes: LiveData<List<Recipe>>

    init {
        val recipeIngredientDao = CookingAppDatabase.getDatabase(application, viewModelScope).recipeIngredientDao()
        val recipeDao = CookingAppDatabase.getDatabase(application,viewModelScope).recipeDao()
        repository = RecipeRepository(recipeDao, recipeIngredientDao)
        allRecipes = repository.allRecipes
        allRecipesWithIngredients = repository.allRecipeWithIngredients
    }

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.insertRecipe(recipe)
    }
    fun removeRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
    }

    fun insertRecipeWithIngredients(recipe: Recipe, ingredients: List<Long>) = viewModelScope.launch {
        Log.d("Insert","$ingredients[0] $ingredients[1]")
        repository.insertRecipeWithIngredients(recipe,ingredients)
    }
}