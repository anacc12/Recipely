package com.example.recipely.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.recipely.Models.Meal
import com.example.recipely.MealRecipeActivity
import com.example.recipely.Models.MealsRecycler
import com.example.recipely.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meals.view.*
import kotlinx.android.synthetic.main.item_category_meals.view.*

class CategoryMealsAdapter(private val meals: MealsRecycler): RecyclerView.Adapter<CategoryMealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val meals = layoutInflater.inflate(R.layout.item_category_meals, parent, false)
        return CategoryMealsViewHolder(meals)
    }

    override fun getItemCount(): Int {
        return meals.meals.count()
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        val meal = meals.meals[position]
        holder.v.category_meal_name.text = meal.strMeal
        Picasso.get()
            .load(meal.strMealThumb)
            .fit().centerInside()
            .error(android.R.drawable.stat_notify_error)
            .into(holder.v.category_meal_img)

        holder.v.item_card.animation = AnimationUtils.loadAnimation(holder.v.context,R.anim.scale)

        holder.meal=meal

    }
}

class CategoryMealsViewHolder(val v: View, var meal: Meal? = null): RecyclerView.ViewHolder(v){

    companion object{
        const val ID_KEY = "ID"
        const val THUMB_MEAL_KEY = "MEAL_THUMB"
        const val NAME_KEY = "NAME"
    }

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, MealRecipeActivity::class.java)

            intent.putExtra(ID_KEY, meal?.idMeal)
            intent.putExtra(THUMB_MEAL_KEY, meal?.strMealThumb)
            intent.putExtra(NAME_KEY, meal?.strMeal)

            v.context.startActivity(intent)
            println("Holder meal id: " + meal?.idMeal)
        }
    }

}