package com.example.klengen.kotlinandroidtry.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients
import com.example.klengen.kotlinandroidtry.database.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var recipesViewModel: SearchViewModel

    var recipeWithIngredients: List<RecipeWithIngredients> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recipesViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        recipesViewModel.searchResult.observe(this, Observer { recipes ->
            recipes?.let{ recipeWithIngredients = recipes
            }
        })

        button.setOnClickListener {
            var s = "${recipeWithIngredients.last().recipe.name}: "
            recipeWithIngredients.last().ingredients.forEach { s = s+it.name+" " }
            Log.d("Test",s)

        }



    }
}
