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
    Ingredient::class,Recipe::class,RecipeIngredientRef::class
    ], version = 19 , exportSchema = false)

abstract class CookingAppDatabase : RoomDatabase(){

    abstract fun ingredientDao(): IngredientDao
    abstract  fun recipeDao(): RecipeDao
    abstract fun recipeIngredientDao(): RecipeIngredientDao

    private class CookingAppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

                    val ingredientDao = database.ingredientDao()
                    val recipeDao = database.recipeDao()
                    val recipeIngredientDao = database.recipeIngredientDao()

                    ingredientDao.deleteAll()
                    recipeDao.deleteAll()

                    populateDatabase(ingredientDao,recipeDao,recipeIngredientDao)
                }
            }
        }

        suspend fun populateDatabase(ingredientDao: IngredientDao, recipeDao:RecipeDao, recipeIngredientDao: RecipeIngredientDao) {

            Log.d("Populate", "Populate Database")
            // Add sample words.
            var ingredient =
                Ingredient("Milch")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Nudeln")
            ingredientDao.insert(ingredient)
            ingredient =
            Ingredient("Basmatireis")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Vollkornreis")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Langkornreis")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Paprika")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Sahne")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Salami")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Schinken")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Eier")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Mehl")
            ingredientDao.insert(ingredient)
            ingredient =
                Ingredient("Tomaten")
            val idI = ingredientDao.insert(ingredient)

            val recipe = Recipe("Tomatensoße")
                val idR = recipeDao.insert(recipe)
            val recipeWithIngredients = RecipeIngredientRef(idR, idI)
            Log.d("recipeWithIngredients","Recipe: $idR Ingredient: $idI")
                recipeIngredientDao.insert(recipeWithIngredients)


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
                ).addCallback(CookingAppDatabaseCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}