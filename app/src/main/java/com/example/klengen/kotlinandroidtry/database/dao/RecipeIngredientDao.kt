package com.example.klengen.kotlinandroidtry.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.klengen.kotlinandroidtry.database.RecipeIngredientRelation

@Dao
interface RecipeIngredientDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRelation(relation: RecipeIngredientRelation)
}