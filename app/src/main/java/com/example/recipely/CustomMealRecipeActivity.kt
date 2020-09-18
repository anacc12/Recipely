package com.example.recipely

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.recipely.Adapters.CustomMealsViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_custom_meal_recipe.*
import kotlinx.android.synthetic.main.activity_new_recipe.*

class CustomMealRecipeActivity: AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_meal_recipe)
        back_btn.setOnClickListener {
            onBackPressed()
        }

        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        getContent()
    }


    private fun getContent(){
        val user = mAuth.currentUser
        val title = intent.getStringExtra(CustomMealsViewHolder.CUSTOM_TITLE_KEY)
        val ref= user?.uid?.let { firestore.collection("users").document(it).collection("recipes").document(title) }

        ref?.get()?.addOnSuccessListener {
            val ingredients : String = it.get("ingredients") as String
            custom_ingredients.text = ingredients

            val instructions : String = it.get("instructions") as String
            custom_instructions.text = instructions

            val uri: String = it.get("photo") as String
            if(uri == "n") {
                meal_img.setImageResource(R.drawable.add_recipe_gradient)
            }
            else {
                val myUri = Uri.parse(uri)
                meal_img.setImageURI(myUri)
            }
        }
        meal_title.text = title
    }

}