package com.example.recipely

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipely.Models.Recipe
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_recipe.*

class NewRecipeActivity: AppCompatActivity() {
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)

        cancel_btn.setOnClickListener {
            onBackPressed()
        }

        save_btn.setOnClickListener {
        val title = title_et.text.toString()
        val ingredients = ingredients_et.text.toString()
        val instructions = instructions_et.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("recipes")
        val id = ref.push().key

        val recipe = id?.let {
            Recipe(
                it,
                title,
                ingredients,
                instructions
            )
        }

        if (id != null) {
            if(title.isBlank())
                title_et.error = "Title should not be blank."
            else if(ingredients.isBlank())
                ingredients_et.error = "Ingredients should not be blank."
            else if (instructions.isBlank())
                instructions_et.error = "Instructions should not be blank."
            else {
                ref.child(id).setValue(recipe).addOnCompleteListener {
                    Toast.makeText(applicationContext, "Saved successfully", Toast.LENGTH_SHORT)
                        .show()
                    onBackPressed()
                }
            }
        }
        }

    }
}