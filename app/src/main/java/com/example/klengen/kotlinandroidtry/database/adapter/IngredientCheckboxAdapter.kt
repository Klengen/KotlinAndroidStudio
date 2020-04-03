package com.example.klengen.kotlinandroidtry.database.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Ingredient

class IngredientCheckboxAdapter internal constructor(var ingredients: List<Ingredient>, private var onIngredientClickListener: OnIngredientClickListener) : RecyclerView.Adapter<IngredientCheckboxAdapter.IngredientCheckboxViewHolder>() {

    override fun getItemCount() = ingredients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientCheckboxViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_ingredient_checkbox, parent,false)
        return IngredientCheckboxViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientCheckboxViewHolder, position: Int) {
        holder.initialize(ingredients[position],onIngredientClickListener)
    }

    inner class IngredientCheckboxViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ingredientName: TextView = itemView.findViewById(R.id.textView)

        fun initialize(ingredient: Ingredient, click: OnIngredientClickListener){
            ingredientName.text = ingredient.name

            itemView.setOnClickListener {
                click.onIngredientClick(ingredient,adapterPosition)
            }
        }
    }

    interface OnIngredientClickListener{
        fun onIngredientClick(ingredient: Ingredient, position: Int)
    }
}