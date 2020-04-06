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
import com.example.klengen.kotlinandroidtry.database.CookingAppViewModel
import com.example.klengen.kotlinandroidtry.database.Recipe
import com.example.klengen.kotlinandroidtry.database.adapter.RecipeListAdapter

import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesActivity : AppCompatActivity(), RecipeListAdapter.OnRecipeClickListener {

    private val newRecipeActivityRequestcode = 11
    private lateinit var ingredientViewModel: CookingAppViewModel

    private var recipes:List<Recipe> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        Log.d("Recipe","Recipename: OnCreate")

        val recyclerView: RecyclerView = recyclerview_recipes_container
        val adapter = RecipeListAdapter(recipes, this)

        ingredientViewModel = ViewModelProvider(this).get(CookingAppViewModel::class.java)
        ingredientViewModel.allRecipes.observe(this, Observer { recipes ->
            recipes?.let { adapter.setIngredients(it) }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabadd.setOnClickListener {
            val intent = Intent(this@RecipesActivity, NewRecipeActivity::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
            super.onActivityResult(requestCode, resultCode, intentData)

        if(resultCode == Activity.RESULT_OK){
            intentData?.getStringExtra(NewRecipeActivity.REPLY_RECIPENAME)?.let{
                val recipe = Recipe(it)
                Log.d("Recipe", "Init id:" +recipe.id)
                ingredientViewModel.insertRecipe(recipe)
            }
            intentData?.getLongArrayExtra(NewRecipeActivity.REPLY_INGREDIENT_ID)?.let {
                var result:String =""
                var ar : List<Long> = it.toList()
                ar.forEach { Log.d("result", it.toString()) }

            }
        }
    }

    override fun onRecipeClick(recipe: Recipe, position: Int) {
        Log.d("Recipe", "Recipe "+recipe.id+" "+recipe.name+" on Position "+position+ " clicked")
    }
}
