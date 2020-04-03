package com.example.klengen.kotlinandroidtry.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.klengen.kotlinandroidtry.database.Ingredient

@Dao
interface IngredientDao{
    @Query("SELECT * from ingredient_table ORDER BY ingredient ASC")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingredient: Ingredient)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(ingredient: Ingredient)

}