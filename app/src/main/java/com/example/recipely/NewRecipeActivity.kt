package com.example.recipely

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipely.Models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meal.*
import kotlinx.android.synthetic.main.activity_new_recipe.*

class NewRecipeActivity: AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        cancel_btn.setOnClickListener {
            onBackPressed()
        }

        photo_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        save_btn.setOnClickListener {
        val title = title_et.text.toString()
        val ingredients = ingredients_et.text.toString()
        val instructions = instructions_et.text.toString()


        if(title.isBlank())
            title_et.error = "Title should not be blank."
        else if(ingredients.isBlank())
            ingredients_et.error = "Ingredients should not be blank."
        else if (instructions.isBlank())
            instructions_et.error = "Instructions should not be blank."
        else
            saveRecipe(title, ingredients, instructions)
        }

    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            photo.setImageBitmap(bitmap)
        }
    }

    private  fun saveRecipe(title: String, ingredients:String, instructions:String){
            val user = mAuth.currentUser
        val recipe = HashMap<String, Any>()
        recipe["title"] = title
        recipe["ingredients"] = ingredients
        recipe["instructions"] = instructions

        if(selectedPhotoUri == null)
            recipe["photo"] = "n"
        else recipe["photo"] = selectedPhotoUri.toString()

        if (user != null) {
            firestore.collection("users").document(user.uid).collection("recipes").document(title).set(recipe).addOnCompleteListener {
                Toast.makeText(applicationContext, "Saved successfully", Toast.LENGTH_SHORT)
                    .show()
                onBackPressed()
            }
        }
    }
}