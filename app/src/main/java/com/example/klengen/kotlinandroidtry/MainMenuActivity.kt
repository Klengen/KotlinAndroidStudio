package com.example.klengen.kotlinandroidtry

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.klengen.kotlinandroidtry.ingredients.IngredientListActivity
import com.example.klengen.kotlinandroidtry.recipes.RecipesActivity
import com.example.klengen.kotlinandroidtry.search.SearchActivity

import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
/*        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        //Listeners for Mainmenu Buttons
        menuButtonIngredient.setOnClickListener{
            val intent = Intent(this, IngredientListActivity::class.java)
            startActivity(intent)
        }
        menuButtonRecipes.setOnClickListener{
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }
        menuButtonSearchRecipe.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

}
