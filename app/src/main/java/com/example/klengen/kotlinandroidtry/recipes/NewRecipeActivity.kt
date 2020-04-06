package com.example.klengen.kotlinandroidtry.recipes

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.CookingAppViewModel
import com.example.klengen.kotlinandroidtry.database.Ingredient
import com.example.klengen.kotlinandroidtry.database.adapter.IngredientCheckboxAdapter
import kotlinx.android.synthetic.main.activity_new_recipe.*
import kotlinx.android.synthetic.main.recyclerview_ingredient_checkbox.view.textView

class NewRecipeActivity : AppCompatActivity(), IngredientCheckboxAdapter.OnIngredientClickListener {

//    private val newIngredientActivityRequestCode = 2
    private lateinit var ingredientViewModel: CookingAppViewModel
    var ingredients: ArrayList<Ingredient> = ArrayList()

    val adapter = IngredientCheckboxAdapter(ingredients,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)



        ingredientViewModel = ViewModelProvider(this).get(CookingAppViewModel::class.java)
        ingredientViewModel.allIngredients.observe(this, Observer { ingredients ->
            ingredients?.let { adapter.ingredients = ingredients }
        })

        val recyclerview: RecyclerView = recyclerview_ingrediends_checkbox
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        button_save.setOnClickListener {
           val recipeName  = recipename_input
            if(recipeName.length()>1){
                val replyIntent = Intent()

//                var selectedIngredients:List<Ingredient>

                //TODO
//                ingredients.forEach{}

                val recipe = recipeName.text.toString()
                replyIntent.putExtra(REPLY_RECIPENAME, recipe)
                replyIntent.putExtra(REPLY_INGREDIENT_ID, adapter.getIdOfSelectedIngredients())
                setResult(Activity.RESULT_OK, replyIntent)
//                ingredientViewModel.insertRecipeWithIngredients(recipe,ingredients)
                finish()
            }else{
                Toast.makeText(this,"Rezepname muss länger sein!",Toast.LENGTH_LONG).show()
            }
        }

    }
    override fun onIngredientClick(ingredient: Ingredient, position: Int) {
        val recyclerview: RecyclerView = recyclerview_ingrediends_checkbox
        Toast.makeText(this,"Geklickt: "+ingredient.name,Toast.LENGTH_LONG).show()
        if(adapter.selectedIngredients.containsKey(ingredient) && position!= null){
            recyclerview.getChildAt(position).textView.setBackgroundColor(Color.LTGRAY)
        }else{
            recyclerview.getChildAt(position).textView.setBackgroundColor(ContextCompat.getColor(this,R.color.background))
        }

    }
    companion object{
        const val REPLY_RECIPENAME = "recipename"
        const val REPLY_INGREDIENT_ID = "ingredient_ids"
    }
}
