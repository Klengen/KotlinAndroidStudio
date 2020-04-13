package com.example.klengen.kotlinandroidtry.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.klengen.kotlinandroidtry.database.dao.RecipeDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeIngredientDao

class RecipeRepository(private val recipeDao: RecipeDao, private val recipeIngredientDao: RecipeIngredientDao){

    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()
    val allRecipeWithIngredients: LiveData<List<RecipeWithIngredients>> = recipeIngredientDao.getRecipesWithIngredients()

    suspend fun insertRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
        Log.d("Recipe", "Recipe Reposotory insert")
    }

    suspend fun insertRecipeWithIngredients(recipe: Recipe,ingredients: List<Long>){
        val id = recipeDao.insert(recipe)
        ingredients.forEach {
            val rir = RecipeIngredientRef(id,it)
            recipeIngredientDao.insert(rir)
        }
    }
}