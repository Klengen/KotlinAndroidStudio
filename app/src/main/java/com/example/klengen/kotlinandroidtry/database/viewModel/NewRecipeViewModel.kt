package com.example.klengen.kotlinandroidtry.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.klengen.kotlinandroidtry.database.CookingAppDatabase
import com.example.klengen.kotlinandroidtry.database.Ingredient
import com.example.klengen.kotlinandroidtry.database.IngredientsRepository

class NewRecipeViewModel (application: Application):AndroidViewModel(application){

    private val repository: IngredientsRepository

    val allIngredients: LiveData<List<Ingredient>>

    init {
        val ingredientDao = CookingAppDatabase.getDatabase(
            application,
            viewModelScope
        ).ingredientDao()

        repository = IngredientsRepository(ingredientDao)
        allIngredients = repository.allIngredients
    }
}