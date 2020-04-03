package com.example.klengen.kotlinandroidtry.database.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Recipe
import kotlinx.android.synthetic.main.recyclerview_recipes.view.*

class RecipeListAdapter internal constructor(var recipes: List<Recipe>, private var onRecipeClickListener: OnRecipeClickListener) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var recipeName: TextView = itemView.textView
 /*       fun initialize(recipe: Recipe, click: OnRecipeClickListener){
            Log.d("Recipe","Recipename: "+recipe.name)
            recipeName.text = recipe.name

            itemView.setOnClickListener {
                click.onRecipeClick(recipe,adapterPosition)
            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_recipes, parent,false)
        Log.d("Recipe","Recipename: Created")
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.recipeName.text = recipes[position].name
    }


    interface OnRecipeClickListener{
        fun onRecipeClick(recipe: Recipe, position: Int)
    }
}