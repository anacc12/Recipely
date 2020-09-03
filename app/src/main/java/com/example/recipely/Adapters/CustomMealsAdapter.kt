package com.example.recipely.Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.recipely.*
import com.example.recipely.Models.MealRecipes
import com.example.recipely.Models.Recipe
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_category_meals.view.*
import kotlinx.android.synthetic.main.item_custom_meal.view.*
import kotlinx.android.synthetic.main.item_custom_meal.view.item_card
import kotlinx.android.synthetic.main.item_discover.view.*

class CustomMealsAdapter(private val mealRecipes: MealRecipes): RecyclerView.Adapter<CustomMealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomMealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recipes = layoutInflater.inflate(R.layout.item_custom_meal, parent, false)
        return CustomMealsViewHolder(recipes)
    }

    override fun getItemCount(): Int {
        return mealRecipes.recipes.count()
    }

    override fun onBindViewHolder(holder: CustomMealsViewHolder, position: Int) {
        val recipe = mealRecipes.recipes[position]
        holder.v.custom_meal_name.text = recipe.title

        holder.recipe = recipe
    }

    fun deleteItem(position: Int){
        deleteFromDatabase(position)
        mealRecipes.recipes.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun deleteFromDatabase(position: Int){
        val key = mealRecipes.recipes[position].id
        val ref = FirebaseDatabase.getInstance().getReference("recipes")
        ref.child(key).removeValue()
    }
}

class CustomMealsViewHolder(val v: View, var recipe: Recipe? = null): RecyclerView.ViewHolder(v){

    companion object{
        const val CUSTOM_TITLE_KEY = "TITLE"
        const val CUSTOM_INGREDIENTS_KEY = "INGREDIENTS"
        const val CUSTOM_INSTRUCTIONS_KEY = "INSTRUCTIONS"
    }

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, CustomMealRecipeActivity::class.java)

            intent.putExtra(CUSTOM_TITLE_KEY, recipe?.title)
            intent.putExtra(CUSTOM_INGREDIENTS_KEY, recipe?.ingredients)
            intent.putExtra(CUSTOM_INSTRUCTIONS_KEY, recipe?.instructions)

            v.context.startActivity(intent)
        }
    }

}