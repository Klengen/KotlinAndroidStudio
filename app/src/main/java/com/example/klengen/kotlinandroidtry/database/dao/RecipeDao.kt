package com.example.klengen.kotlinandroidtry.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.klengen.kotlinandroidtry.database.Recipe

@Dao
interface RecipeDao{
    @Query("SELECT * from recipe_table ORDER BY recipe ASC")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipe: Recipe):Long

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAll()

    @Delete
    suspend fun  delete(recipe: Recipe)
}