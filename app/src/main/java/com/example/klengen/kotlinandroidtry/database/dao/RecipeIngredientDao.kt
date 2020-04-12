package com.example.klengen.kotlinandroidtry.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.klengen.kotlinandroidtry.database.RecipeIngredientRef
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients


@Dao
interface RecipeIngredientDao {
    @Transaction
    @Query("SELECT * FROM recipe_table")
    fun getRecipesWithIngredients():LiveData<List<RecipeWithIngredients>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(recipeWithIngredients: RecipeIngredientRef)
}