package com.example.klengen.kotlinandroidtry.database.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
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
        holder.initialize(ingredients[position],onIngredientClickListener)
    }

    inner class IngredientCheckboxViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ingredientName: TextView = itemView.findViewById(R.id.textView)

        fun initialize(ingredient: Ingredient, click: OnIngredientClickListener){
            ingredientName.text = ingredient.name

            itemView.setOnClickListener {
                toggleIngredient(ingredient)
                click.onIngredientClick(ingredient,adapterPosition)
            }
        }

        fun toggleIngredient(ingredient: Ingredient){
            if(selectedIngredients.containsKey(ingredient)){
                selectedIngredients.remove(ingredient)
            }else{
                selectedIngredients[ingredient] = ingredients.indexOf(ingredient)
            }
            Log.d("Selected", "Selected ingredients "+selectedIngredients.size)
        }
    }

    fun getIngredient(position: Int): Ingredient {
        return ingredients[position]
    }

    /*fun getIdOfSelectedIngredients():ArrayList<Long>{
        Log.d("Selected", "getIdOfSelectedIngredients")
        var sel: ArrayList<Long> = ArrayList(selectedIngredients.size)
        var i = 0
        Log.d("Selected", "Selected ingredients "+selectedIngredients.size)
        selectedIngredients.forEach{
            sel[i++]=it.key.id
        }
        return sel
    }*/

    fun getIdOfSelectedIngredients() = selectedIngredients.map { it.key.id }.toLongArray()

    interface OnIngredientClickListener{
        fun onIngredientClick(ingredient: Ingredient, position: Int)
    }
}