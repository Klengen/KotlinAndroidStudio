package com.example.klengen.kotlinandroidtry.recipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Recipe
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients
import com.example.klengen.kotlinandroidtry.database.viewModel.RecipeViewModel
import com.example.klengen.kotlinandroidtry.database.adapter.RecipeListAdapter

import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesActivity : AppCompatActivity(), RecipeListAdapter.OnRecipeClickListener {

    private val newRecipeActivityRequestCode = 11
    private val recipeDetailsRequestCode = 10
    private lateinit var recipeViewModel: RecipeViewModel


    private var recipes:List<RecipeWithIngredients> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        Log.d("Recipe","Recipename: OnCreate")

        val recyclerView: RecyclerView = recyclerview_recipes_container
        val adapter = RecipeListAdapter(recipes, this)

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.allRecipesWithIngredients.observe(this, Observer { recipes ->
            recipes?.let { adapter.setRecipes(it) }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabadd.setOnClickListener {
            val intent = Intent(this@RecipesActivity, NewRecipeActivity::class.java)
            startActivityForResult(intent,newRecipeActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
            super.onActivityResult(requestCode, resultCode, intentData)

        if(resultCode == Activity.RESULT_OK && requestCode == newRecipeActivityRequestCode){
            intentData?.getStringExtra(NewRecipeActivity.REPLY_RECIPENAME)?.let{ recipe ->
                val recipe = Recipe(recipe)

                intentData.getLongArrayExtra(NewRecipeActivity.REPLY_INGREDIENT_ID)?.let { ingredients ->
                    recipeViewModel.insertRecipeWithIngredients(recipe,ingredients.toList())
                }
            }

        }
        if(resultCode == Activity.RESULT_OK && requestCode == recipeDetailsRequestCode){


        }

    }

    override fun onRecipeClick(recipe: RecipeWithIngredients, position: Int) {

        val intent = Intent(this@RecipesActivity, RecipeInformation::class.java)
        intent.putExtra(RecipeInformation.INTENT_EXTRA_RECIPE,recipe.recipe.id)
        startActivityForResult(intent,recipeDetailsRequestCode)
    }


}
