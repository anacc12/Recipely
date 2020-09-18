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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_category_meals.view.*
import kotlinx.android.synthetic.main.item_custom_meal.view.*
import kotlinx.android.synthetic.main.item_custom_meal.view.item_card
import kotlinx.android.synthetic.main.item_discover.view.*

class CustomMealsAdapter(private val titles: MutableList<String>): RecyclerView.Adapter<CustomMealsViewHolder>() {
    private val firestore = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    val user = mAuth.currentUser

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomMealsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val recipes = layoutInflater.inflate(R.layout.item_custom_meal, parent, false)
        return CustomMealsViewHolder(recipes)
    }

    override fun getItemCount(): Int {
        return titles.count()
    }

    override fun onBindViewHolder(holder: CustomMealsViewHolder, position: Int) {
        val title = titles[position]
        holder.v.custom_meal_name.text = title
        holder.title = title
    }

    fun deleteItem(position: Int){
        deleteFromDatabase(position)
        titles.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    private fun deleteFromDatabase(position: Int){
        val title = titles[position]
        user?.uid?.let {
            firestore.collection("users")
                .document(it)
                .collection("recipes")
                .document(title)
                .delete()
        }
    }
}

class CustomMealsViewHolder(val v: View, var title: String? = null): RecyclerView.ViewHolder(v){

    companion object{
        const val CUSTOM_TITLE_KEY = "TITLE"
    }

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, CustomMealRecipeActivity::class.java)

            intent.putExtra(CUSTOM_TITLE_KEY, title)
            v.context.startActivity(intent)
        }
    }

}