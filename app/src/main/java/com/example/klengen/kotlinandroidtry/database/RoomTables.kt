package com.example.klengen.kotlinandroidtry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @ColumnInfo(name = "ingredient") val name: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ingredientId") val id : Long = 0)


@Entity(tableName = "recipe_table")
data class Recipe(
    @ColumnInfo(name = "recipe") val name: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "recipeId") val id: Long = 0)

@Entity(tableName = "recipe_ingredient_table", primaryKeys = ["recipeId","ingredientId"])
data class RecipeIngredientRef(
    @ColumnInfo(name = "recipeId")val recipeId: Long,
    @ColumnInfo(name = "ingredientId")val ingredientId:Long
)