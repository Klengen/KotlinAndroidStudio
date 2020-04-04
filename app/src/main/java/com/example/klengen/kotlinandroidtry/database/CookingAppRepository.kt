package com.example.klengen.kotlinandroidtry.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.klengen.kotlinandroidtry.database.dao.IngredientDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeDao

class CookingAppRepository (private val ingredientDao: IngredientDao, private val recipeDao: RecipeDao){

    val allIngredients: LiveData<List<Ingredient>> = ingredientDao.getAllIngredients()
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun insertIngredient(ingredient: Ingredient){
        ingredientDao.insert(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredient){
        ingredientDao.delete(ingredient)
    }

    suspend fun insertRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
        Log.d("Recipe", "Recipe Reposotory insert")
    }

}