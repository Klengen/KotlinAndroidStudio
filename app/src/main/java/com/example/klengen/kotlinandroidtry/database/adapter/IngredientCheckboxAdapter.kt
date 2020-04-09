package com.example.klengen.kotlinandroidtry.database.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.Ingredient

class IngredientCheckboxAdapter internal constructor(var ingredients: List<Ingredient>, private var onIngredientClickListener: OnIngredientClickListener) : RecyclerView.Adapter<IngredientCheckboxAdapter.IngredientCheckboxViewHolder>() {

        var selectedIngredients: MutableMap<Ingredient,Int> = mutableMapOf()

        override fun getItemCount() = ingredients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientCheckboxViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_ingredient_checkbox, parent,false)
        return IngredientCheckboxViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientCheckboxViewHolder, position: Int) {
        holder.initialize(getIngredient(position),onIngredientClickListener)
    }

    inner class IngredientCheckboxViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ingredientName: TextView = itemView.findViewById(R.id.textView)

        fun initialize(ingredient: Ingredient, click: OnIngredientClickListener){
            ingredientName.text = ingredient.name

            itemView.setOnClickListener {
                toggleIngredient(adapterPosition)
                click.onIngredientClick(ingredient,layoutPosition)
            }
        }

        private fun toggleIngredient(position: Int){
            var ingredient: Ingredient = getIngredient(position)
            Log.d("Selected", "Selected ingredient posi: $position name: ${ingredient.name}")
            if(selectedIngredients.containsKey(ingredient)){
                selectedIngredients.remove(ingredient)
            }else{
                selectedIngredients[ingredient] = position
            }

        }
    }

    fun getIngredient(position: Int) = ingredients[position]

    fun getIdOfSelectedIngredients() = selectedIngredients.map { it.key.id }.toLongArray()

    interface OnIngredientClickListener{
        fun onIngredientClick(ingredient: Ingredient, position: Int)
    }
}