package com.example.klengen.kotlinandroidtry.database

import androidx.lifecycle.LiveData
import com.example.klengen.kotlinandroidtry.database.dao.IngredientDao

class IngredientsRepository (private val ingredientDao: IngredientDao){
    val allIngredients: LiveData<List<Ingredient>> = ingredientDao.getAllIngredients()

    suspend fun insertIngredient(ingredient: Ingredient){
        ingredientDao.insert(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredient){
        ingredientDao.delete(ingredient)
    }
}