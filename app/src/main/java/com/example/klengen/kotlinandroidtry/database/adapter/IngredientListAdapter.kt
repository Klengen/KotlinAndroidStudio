package com.example.klengen.kotlinandroidtry.database.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klengen.kotlinandroidtry.R
import com.example.klengen.kotlinandroidtry.database.CookingAppViewModel
import com.example.klengen.kotlinandroidtry.database.Ingredient
import kotlinx.android.synthetic.main.recyclerview_ingredient.view.*

class IngredientListAdapter internal constructor() : RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    private var ingredients = emptyList<Ingredient>() // Cached copy of ingredients

    override fun getItemCount() = ingredients.size

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientItemTextView: TextView = itemView.textView
        val ingredientItemButton: Button = itemView.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_ingredient, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val current = ingredients[position]
//        Log.d("LOG", ingredients[position].ingredient)
        holder.ingredientItemTextView.text = current.name
        holder.ingredientItemButton.setOnClickListener {
            val ingredientViewModel =
                CookingAppViewModel(
                    application = Application()
                )
            ingredientViewModel.removeIngredient(this.ingredients[position])
        }
    }

    internal fun setIngredients(ingredients: List<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

}