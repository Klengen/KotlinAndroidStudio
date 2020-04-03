package com.example.klengen.kotlinandroidtry.database

import androidx.room.Entity

@Entity(tableName = "recipe_ingredient_table", primaryKeys = ["recipeId","ingredientId"])
data class RecipeIngredientRelation(
    var ingredientId:Long,
    val recipeId: Long
)