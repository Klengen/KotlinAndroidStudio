package com.example.klengen.kotlinandroidtry.recipes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Ingredient
import com.example.klengen.kotlinandroidtry.database.viewModel.IngredientViewModel
import com.example.klengen.kotlinandroidtry.database.adapter.IngredientCheckboxAdapter
import com.example.klengen.kotlinandroidtry.database.viewModel.NewRecipeViewModel
import kotlinx.android.synthetic.main.activity_new_recipe.*
import kotlinx.android.synthetic.main.recyclerview_ingredient_checkbox.view.*

class NewRecipeActivity : AppCompatActivity(), IngredientCheckboxAdapter.OnIngredientClickListener {

//    private val newIngredientActivityRequestCode = 2
    private lateinit var ingredientViewModel: NewRecipeViewModel
    private lateinit var adapter: IngredientCheckboxAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    var ingredients: ArrayList<Ingredient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)

        adapter = IngredientCheckboxAdapter(ingredients,this)
        linearLayoutManager = LinearLayoutManager(this)

        ingredientViewModel = ViewModelProvider(this).get(NewRecipeViewModel::class.java)
        ingredientViewModel.allIngredients.observe(this, Observer { ingredients ->
            ingredients?.let { adapter.ingredients = ingredients }
        })

        val recyclerview: RecyclerView = recyclerview_ingrediends_checkbox
        recyclerview.adapter = adapter
        recyclerview.layoutManager = linearLayoutManager

        button_save.setOnClickListener {
           val recipeName  = recipename_input
            if(recipeName.length()>1){
                val replyIntent = Intent()

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
        val child = recyclerview.getChildAt(position-linearLayoutManager.findFirstVisibleItemPosition())
        Toast.makeText(this,"Geklickt: "+ingredient.name,Toast.LENGTH_LONG).show()
        if(adapter.selectedIngredients.containsKey(ingredient)){
            child.setBackgroundResource(R.drawable.selectable_recyclerview_selected)
            child.check.visibility = View.VISIBLE
//            child.textView.setTextColor(R.color.selected_font_color)
            Log.d("Clicked","${ingredient.id}  ${ingredient.name}")

        }else{
            child.setBackgroundResource(R.drawable.selectable_recyclerview)
            child.check.visibility = View.INVISIBLE
//            child.textView.setTextColor(R.color.basic_font_color)
        }

    }
    companion object{
        const val REPLY_RECIPENAME = "recipename"
        const val REPLY_INGREDIENT_ID = "ingredient_ids"
    }
}
