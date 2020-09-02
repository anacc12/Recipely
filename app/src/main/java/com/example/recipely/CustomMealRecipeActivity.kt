package com.example.recipely

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipely.Adapters.CustomMealsViewHolder
import kotlinx.android.synthetic.main.activity_custom_meal_recipe.*

class CustomMealRecipeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_meal_recipe)
        back_btn.setOnClickListener {
            onBackPressed()
        }
        getContent()
    }


    private fun getContent(){
        val title = intent.getStringExtra(CustomMealsViewHolder.CUSTOM_TITLE_KEY)
        val ingredients = intent.getStringExtra(CustomMealsViewHolder.CUSTOM_INGREDIENTS_KEY)
        val instructions = intent.getStringExtra(CustomMealsViewHolder.CUSTOM_INSTRUCTIONS_KEY)

        meal_title.text = title
        custom_ingredients.text = ingredients
        custom_instructions.text = instructions
    }

}