package com.example.klengen.kotlinandroidtry.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.klengen.kotlinandroidtry.database.dao.IngredientDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeIngredientDao

class CookingAppRepository (private val ingredientDao: IngredientDao, private val recipeDao: RecipeDao, private val recipeIngredientDao: RecipeIngredientDao){

    val allIngredients: LiveData<List<Ingredient>> = ingredientDao.getAllIngredients()
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()
    val allRecipeWithIngredients: LiveData<List<RecipeWithIngredients>> = recipeIngredientDao.getRecipesWithIngredients()

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

    suspend fun insertRecipeWithIngredients(recipe: Recipe,ingredients: List<Ingredient>){
        ingredients.forEach {
            val rir = RecipeIngredientRef(recipe.id,it.id)
            recipeIngredientDao.insert(rir)
        }
    }

}