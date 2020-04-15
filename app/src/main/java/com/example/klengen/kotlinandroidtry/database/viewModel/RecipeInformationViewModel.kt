package com.example.klengen.kotlinandroidtry.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.klengen.kotlinandroidtry.database.CookingAppDatabase
import com.example.klengen.kotlinandroidtry.database.RecipeRepository
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients

class RecipeInformationViewModel (application: Application, id:Long):AndroidViewModel(application){

    private val repository: RecipeRepository

    var recipe: LiveData<RecipeWithIngredients>

    init {
        val recipeIngredientDao = CookingAppDatabase.getDatabase( application,viewModelScope).recipeIngredientDao()
        val recipeDao =  CookingAppDatabase.getDatabase( application,viewModelScope).recipeDao()

        repository = RecipeRepository(recipeDao,recipeIngredientDao)
        recipe = repository.getIngredientsOfRecipe(recipeId)
    }

}