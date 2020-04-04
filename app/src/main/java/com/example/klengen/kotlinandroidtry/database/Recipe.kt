package com.example.klengen.kotlinandroidtry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @ColumnInfo(name = "recipe") val name: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0)