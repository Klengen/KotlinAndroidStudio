package com.example.klengen.kotlinandroidtry.recipes

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
import com.example.klengen.kotlinandroidtry.database.adapter.IngredientCheckboxAdapter
import kotlinx.android.synthetic.main.activity_new_recipe.*

class NewRecipeActivity : AppCompatActivity(), IngredientCheckboxAdapter.OnIngredientClickListener {

//    private val newIngredientActivityRequestCode = 2
    private lateinit var ingredientViewModel: CookingAppViewModel
    var ingredients: ArrayList<Ingredient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)

        val adapter =
            IngredientCheckboxAdapter(ingredients,this)

        ingredientViewModel = ViewModelProvider(this).get(CookingAppViewModel::class.java)
        ingredientViewModel.allIngredients.observe(this, Observer { ingredients ->
            ingredients?.let { adapter.ingredients = ingredients }
        })

        val recyclerview = recyclerview_ingrediends_checkbox
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

       /* button_save.setOnClickListener {
           val recipeName  = recipename_input
            if(recipeName.length()>1){
                val replyIntent = Intent()

//                var selectedIngredients:List<Ingredient>

                //TODO
//                ingredients.forEach{}

                val recipe = recipeName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, recipe)
                setResult(Activity.RESULT_OK, replyIntent)
//                ingredientViewModel.insertRecipeWithIngredients(recipe,ingredients)
            }else{
                Toast.makeText(this,"Rezepname muss länger sein!",Toast.LENGTH_LONG).show()
            }
        }

        */

    }
    override fun onIngredientClick(ingredient: Ingredient, position: Int) {
        Toast.makeText(this,"Geklickt: "+ingredient.name,Toast.LENGTH_LONG).show()
    }
    companion object{
        const val EXTRA_REPLY = ""
    }
}
