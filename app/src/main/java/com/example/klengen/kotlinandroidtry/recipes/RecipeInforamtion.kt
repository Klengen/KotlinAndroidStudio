package com.example.klengen.kotlinandroidtry.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Recipe
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients
import com.example.klengen.kotlinandroidtry.database.viewModel.RecipeInformationViewModel
import kotlinx.android.synthetic.main.activity_recipe_information.*

class RecipeInformation : AppCompatActivity() {

    private lateinit var recipeInformationViewModel: RecipeInformationViewModel

    var recipeWithIngredients: RecipeWithIngredients = RecipeWithIngredients(Recipe("",-1),
        emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_information)

//        val id : Long = intent.getLongExtra(INTENT_EXTRA_RECIPE,-1)

        recipeInformationViewModel = ViewModelProvider(this).get(RecipeInformationViewModel::class.java)
        recipeInformationViewModel.recipe.observe(this, Observer { recipe ->
            recipe?.let { recipeWithIngredients = recipe }
        })

        button.setOnClickListener {
                var s = "${recipeWithIngredients.recipe.name}: "
                recipeWithIngredients.ingredients.forEach { s = s+it.name+" " }
                Log.d("Test",s)

            }
    }
    companion object{
        val INTENT_EXTRA_RECIPE:String = "recipeId"
 //       var id:Long = getRecipeId()
    }

    fun getRecipeId():Long{
        return intent.getLongExtra(INTENT_EXTRA_RECIPE,-1)
    }
}
