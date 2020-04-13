package com.example.klengen.kotlinandroidtry.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

class SearchViewModel (application: Application):AndroidViewModel(application){

    private val repository:RecipeRepository

    val searchResult: LiveData<List<RecipeWithIngredients>>

    init {
        val recipeIngredientDao = CookingAppDatabase.getDatabase(application, viewModelScope).recipeIngredientDao()
        val recipeDao = CookingAppDatabase.getDatabase(application,viewModelScope).recipeDao()
        repository = RecipeRepository(recipeDao, recipeIngredientDao)
        searchResult = repository.allRecipeWithIngredients
    }

}