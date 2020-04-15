package com.example.klengen.kotlinandroidtry.database.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Recipe
import com.example.klengen.kotlinandroidtry.database.RecipeWithIngredients
import kotlinx.android.synthetic.main.recyclerview_recipes.view.*

class RecipeListAdapter internal constructor(private var recipes: List<RecipeWithIngredients>, private var onRecipeClickListener: OnRecipeClickListener) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.textView

        fun initialize(recipe: RecipeWithIngredients,click: OnRecipeClickListener){
            recipeName.text = recipe.recipe.name

            itemView.setOnClickListener{
                click.onRecipeClick(recipe,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_recipes, parent,false)
        Log.d("Recipe","Recipename: Created")
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Log.d("Recipe", "OnBindViewHolder")
        holder.initialize(recipes[position],onRecipeClickListener)
    }

    internal fun setRecipes(recipes: List<RecipeWithIngredients>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    interface OnRecipeClickListener{
        fun onRecipeClick(recipe: RecipeWithIngredients, position: Int)
    }
}