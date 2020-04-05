package com.example.klengen.kotlinandroidtry.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.klengen.kotlinandroidtry.database.dao.IngredientDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeDao
import com.example.klengen.kotlinandroidtry.database.dao.RecipeIngredientDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [
    Ingredient::class,Recipe::class
    ], version = 15 , exportSchema = false)

abstract class CookingAppDatabase : RoomDatabase(){

    abstract fun ingredientDao(): IngredientDao
    abstract  fun recipeDao(): RecipeDao

    private class CookingAppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

                    val ingredientDao = database.ingredientDao()
                    val recipeDao = database.recipeDao()
//                    val recipeIngredientDao = database.recipeIngredientRelation()

                    ingredientDao.deleteAll()
                    recipeDao.deleteAll()

                    populateDatabase(ingredientDao,recipeDao)
                }
            }
        }

        suspend fun populateDatabase(ingredientDao: IngredientDao, recipeDao: RecipeDao) {

            Log.d("Populate", "Populate Database")
            // Add sample words.
            var ingredient =
                Ingredient("Tomaten")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Milch")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Nudeln")
            ingredientDao.insert(ingredient)

            var recipe = Recipe("Tomatensoße")
                recipeDao.insert(recipe)
            recipe = Recipe("Reis mit Curry")
            recipeDao.insert(recipe)

//            val recipeIngredient = RecipeIngredientRelation(0,0)

        }
    }

    companion object {

        @Volatile
        private var INSTANCE: CookingAppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CookingAppDatabase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CookingAppDatabase::class.java,
                    "cooking_database"
                ).addCallback(
                    CookingAppDatabaseCallback(
                        scope
                    )
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}