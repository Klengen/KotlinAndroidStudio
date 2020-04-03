package com.example.klengen.kotlinandroidtry.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CookingAppViewModel(application: Application) : AndroidViewModel(application){

    private val repository: CookingAppRepository

    val allIngredients: LiveData<List<Ingredient>>
    val allRecipes: LiveData<List<Recipe>>

    init {
        val ingredientDao = CookingAppDatabase.getDatabase(application, viewModelScope).ingredientDao()
        val recipeDao = CookingAppDatabase.getDatabase(application,viewModelScope).recipeDao()
        val recipeIngredientDao = CookingAppDatabase.getDatabase(application,viewModelScope).recipeIngredientRelation()
        repository =
            CookingAppRepository(
                ingredientDao,recipeDao,recipeIngredientDao
            )
        allIngredients = repository.allIngredients
        allRecipes = repository.allRecipes
    }

    fun insertIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.insertIngredient(ingredient)
    }

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.insertRecipe(recipe)
        Log.d("Recipe", "Recipe View Model insert")
    }

    fun removeIngredient(ingredient: Ingredient) = viewModelScope.launch {
        repository.deleteIngredient(ingredient)
    }

    fun removeRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
    }
}