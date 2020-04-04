package com.example.klengen.kotlinandroidtry.database.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Ingredient
import com.example.klengen.kotlinandroidtry.database.Recipe
import kotlinx.android.synthetic.main.recyclerview_recipes.view.*

class RecipeListAdapter internal constructor() : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    private var recipes = emptyList<Recipe>()

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_recipes, parent,false)
        Log.d("Recipe","Recipename: Created")
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Log.d("Recipe", "OnBindViewHolder")
        holder.recipeName.text = recipes[position].name
    }

    internal fun setIngredients(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    interface OnRecipeClickListener{
        fun onRecipeClick(recipe: Recipe, position: Int)
    }
}