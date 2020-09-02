package com.example.recipely

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipely.Models.Recipes
import com.example.recipely.Adapters.CategoryMealsViewHolder.Companion.ID_KEY
import com.example.recipely.Adapters.CategoryMealsViewHolder.Companion.NAME_KEY
import com.example.recipely.Adapters.CategoryMealsViewHolder.Companion.THUMB_MEAL_KEY
import com.example.recipely.Api.Client
import com.example.recipely.Api.Service
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meal.*


class MealRecipeActivity: AppCompatActivity() {

     private lateinit var service : Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        back_btn_meal.setOnClickListener {
            onBackPressed()
        }
        service = Client.client.create(Service::class.java)

        setIntentData()

        val id = intent.getStringExtra(ID_KEY)
        fetchRecipe(id)
    }

    private fun setIntentData() {
        val thumb = intent.getStringExtra(THUMB_MEAL_KEY)
        val name = intent.getStringExtra(NAME_KEY)

        meal_text.text = name
        Picasso.get()
            .load(thumb)
            .fit().centerInside()
            .error(android.R.drawable.stat_notify_error)
            .into(meal_img)
    }

    private fun fetchRecipe(id: String) {

        val call = service.fetchMealRecipe(id)
        call.enqueue(object: retrofit2.Callback<Recipes> {

            override fun onResponse(call: retrofit2.Call<Recipes>, response: retrofit2.Response<Recipes>) {
                val recipes = response.body()
                if (recipes != null) {
                    val mealRecipe = recipes.meals[0]

                    runOnUiThread {
                        instructions_tv.text = mealRecipe.strInstructions

                        //INGREDIENTS

                        if (!mealRecipe.strIngredient1.isNullOrBlank())
                            ing_1.text = mealRecipe.strIngredient1
                        else ing_1.visibility = View.GONE

                        if (!mealRecipe.strIngredient2.isNullOrBlank())
                            ing_2.text = mealRecipe.strIngredient2
                        else ing_2.visibility = View.GONE

                        if (!mealRecipe.strIngredient3.isNullOrBlank())
                            ing_3.text = mealRecipe.strIngredient3
                        else ing_3.visibility = View.GONE

                        if (!mealRecipe.strIngredient4.isNullOrBlank())
                            ing_4.text = mealRecipe.strIngredient4
                        else ing_4.visibility = View.GONE

                        if (!mealRecipe.strIngredient5.isNullOrBlank())
                            ing_5.text = mealRecipe.strIngredient5
                        else ing_5.visibility = View.GONE

                        if (!mealRecipe.strIngredient6.isNullOrBlank())
                            ing_6.text = mealRecipe.strIngredient6
                        else ing_6.visibility = View.GONE

                        if (!mealRecipe.strIngredient7.isNullOrBlank())
                            ing_7.text = mealRecipe.strIngredient7
                        else ing_7.visibility = View.GONE

                        if (!mealRecipe.strIngredient8.isNullOrBlank())
                            ing_8.text = mealRecipe.strIngredient8
                        else ing_8.visibility = View.GONE

                        if (!mealRecipe.strIngredient9.isNullOrBlank())
                            ing_9.text = mealRecipe.strIngredient9
                        else ing_9.visibility = View.GONE

                        if (!mealRecipe.strIngredient10.isNullOrBlank())
                            ing_10.text = mealRecipe.strIngredient10
                        else ing_10.visibility = View.GONE

                        if (!mealRecipe.strIngredient11.isNullOrBlank())
                            ing_11.text = mealRecipe.strIngredient11
                        else ing_11.visibility = View.GONE

                        if (!mealRecipe.strIngredient12.isNullOrBlank())
                            ing_12.text = mealRecipe.strIngredient12
                        else ing_12.visibility = View.GONE

                        if (!mealRecipe.strIngredient13.isNullOrBlank())
                            ing_13.text = mealRecipe.strIngredient13
                        else ing_13.visibility = View.GONE

                        if (!mealRecipe.strIngredient14.isNullOrBlank())
                            ing_14.text = mealRecipe.strIngredient14
                        else ing_14.visibility = View.GONE

                        if (!mealRecipe.strIngredient15.isNullOrBlank())
                            ing_15.text = mealRecipe.strIngredient15
                        else ing_15.visibility = View.GONE

                        if (!mealRecipe.strIngredient16.isNullOrBlank())
                            ing_16.text = mealRecipe.strIngredient16
                        else ing_16.visibility = View.GONE

                        if (!mealRecipe.strIngredient17.isNullOrBlank())
                            ing_17.text = mealRecipe.strIngredient17
                        else ing_17.visibility = View.GONE

                        if (!mealRecipe.strIngredient18.isNullOrBlank())
                            ing_18.text = mealRecipe.strIngredient18
                        else ing_18.visibility = View.GONE

                        if (!mealRecipe.strIngredient19.isNullOrBlank())
                            ing_19.text = mealRecipe.strIngredient19
                        else ing_19.visibility = View.GONE

                        if (!mealRecipe.strIngredient20.isNullOrBlank())
                            ing_20.text = mealRecipe.strIngredient20
                        else ing_20.visibility = View.GONE


                        // MEASURES


                        if (!mealRecipe.strMeasure1.isNullOrBlank())
                            amount_1.text = mealRecipe.strMeasure1
                        else amount_1.visibility = View.GONE

                        if (!mealRecipe.strMeasure2.isNullOrBlank())
                            amount_2.text = mealRecipe.strMeasure2
                        else amount_2.visibility = View.GONE

                        if (!mealRecipe.strMeasure3.isNullOrBlank())
                            amount_3.text = mealRecipe.strMeasure3
                        else amount_3.visibility = View.GONE

                        if (!mealRecipe.strMeasure4.isNullOrBlank())
                            amount_4.text = mealRecipe.strMeasure4
                        else amount_4.visibility = View.GONE

                        if (!mealRecipe.strMeasure5.isNullOrBlank())
                            amount_5.text = mealRecipe.strMeasure5
                        else amount_5.visibility = View.GONE

                        if (!mealRecipe.strMeasure6.isNullOrBlank())
                            amount_6.text = mealRecipe.strMeasure6
                        else amount_6.visibility = View.GONE

                        if (!mealRecipe.strMeasure7.isNullOrBlank())
                            amount_7.text = mealRecipe.strMeasure7
                        else amount_7.visibility = View.GONE

                        if (!mealRecipe.strMeasure8.isNullOrBlank())
                            amount_8.text = mealRecipe.strMeasure8
                        else amount_8.visibility = View.GONE

                        if (!mealRecipe.strMeasure9.isNullOrBlank())
                            amount_9.text = mealRecipe.strMeasure9
                        else amount_9.visibility = View.GONE

                        if (!mealRecipe.strMeasure10.isNullOrBlank())
                            amount_10.text = mealRecipe.strMeasure10
                        else amount_10.visibility = View.GONE

                        if (!mealRecipe.strMeasure11.isNullOrBlank())
                            amount_11.text = mealRecipe.strMeasure11
                        else amount_11.visibility = View.GONE

                        if (!mealRecipe.strMeasure12.isNullOrBlank())
                            amount_12.text = mealRecipe.strMeasure12
                        else amount_12.visibility = View.GONE

                        if (!mealRecipe.strMeasure13.isNullOrBlank())
                            amount_13.text = mealRecipe.strMeasure13
                        else amount_13.visibility = View.GONE

                        if (!mealRecipe.strMeasure14.isNullOrBlank())
                            amount_14.text = mealRecipe.strMeasure14
                        else amount_14.visibility = View.GONE

                        if (!mealRecipe.strMeasure15.isNullOrBlank())
                            amount_15.text = mealRecipe.strMeasure15
                        else amount_15.visibility = View.GONE

                        if (!mealRecipe.strMeasure16.isNullOrBlank())
                            amount_16.text = mealRecipe.strMeasure16
                        else amount_16.visibility = View.GONE

                        if (!mealRecipe.strMeasure17.isNullOrBlank())
                            amount_17.text = mealRecipe.strMeasure17
                        else amount_17.visibility = View.GONE

                        if (!mealRecipe.strMeasure18.isNullOrBlank())
                            amount_18.text = mealRecipe.strMeasure18
                        else amount_18.visibility = View.GONE

                        if (!mealRecipe.strMeasure19.isNullOrBlank())
                            amount_19.text = mealRecipe.strMeasure19
                        else amount_19.visibility = View.GONE

                        if (!mealRecipe.strMeasure20.isNullOrBlank())
                            amount_20.text = mealRecipe.strMeasure20
                        else amount_20.visibility = View.INVISIBLE


                        youtube_btn.setOnClickListener {
                            val youtube = mealRecipe.strYoutube
                            if (youtube?.isNotBlank()!!) {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(youtube)
                                intent.setPackage("com.google.android.youtube")
                                startActivity(intent)
                            } else
                                Toast.makeText(
                                    applicationContext,
                                    "Youtube link not available.",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }

                        source_btn.setOnClickListener {
                            val source = mealRecipe.strSource
                            if (source?.isNotBlank()!!) {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(source)
                                startActivity(intent)
                            } else
                                Toast.makeText(
                                    applicationContext,
                                    "Source not available.",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<Recipes>, t: Throwable) {
                println("Loading failed.")
            }
        })
    }
}