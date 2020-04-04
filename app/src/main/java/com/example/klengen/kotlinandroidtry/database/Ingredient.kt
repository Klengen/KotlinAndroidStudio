package com.example.klengen.kotlinandroidtry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @ColumnInfo(name = "ingredient") val name: String,
    @PrimaryKey(autoGenerate = true) val id : Long = 0)
