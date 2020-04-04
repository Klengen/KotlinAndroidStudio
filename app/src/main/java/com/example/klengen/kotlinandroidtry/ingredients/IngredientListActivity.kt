package com.example.klengen.kotlinandroidtry.ingredients

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.CookingAppViewModel
import com.example.klengen.kotlinandroidtry.database.Ingredient
import com.example.klengen.kotlinandroidtry.database.adapter.IngredientListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_ingredient_list.*

class IngredientListActivity : AppCompatActivity() {

    private val newIngredientActivityRequestCode = 1
    private lateinit var ingredientViewModel: CookingAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_list)

        val recyclerView = recyclerview_ingredients_container
        val adapter = IngredientListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        ingredientViewModel = ViewModelProvider(this).get(CookingAppViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        ingredientViewModel.allIngredients.observe(this, Observer { ingredients ->
            // Update the cached copy of the words in the adapter.
            ingredients?.let { adapter.setIngredients(it) }
        })

        val fabadd = findViewById<FloatingActionButton>(R.id.fabadd)
        fabadd.setOnClickListener {
            val intent = Intent(this@IngredientListActivity, NewIngredientActivity::class.java)
            startActivityForResult(intent, newIngredientActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newIngredientActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewIngredientActivity.EXTRA_REPLY)?.let {
                val ingredient =
                    Ingredient(it)
                ingredientViewModel.insertIngredient(ingredient)
                Unit
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
