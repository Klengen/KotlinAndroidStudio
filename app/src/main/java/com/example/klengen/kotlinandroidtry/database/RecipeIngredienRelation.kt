package com.example.klengen.kotlinandroidtry.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded
    val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entity = Ingredient::class,
        entityColumn = "ingredientId",
        associateBy = Junction(value = RecipeIngredientRef::class,
            parentColumn = "recipeId",
            entityColumn = "ingredientId"
        )
    )
    val ingredients: List<Ingredient> = emptyList()
)