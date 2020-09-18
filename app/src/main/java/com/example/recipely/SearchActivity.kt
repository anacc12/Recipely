package com.example.recipely

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipely.Api.Client
import com.example.recipely.Api.Service
import com.example.recipely.Models.Recipes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var service : Service
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = Client.client.create(Service::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search By Name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchRecipe(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun fetchRecipe(name: String){
        setContentView(R.layout.activity_search)
        val call = service.fetchMealByName(name)
        call.enqueue(object: retrofit2.Callback<Recipes> {

            override fun onResponse(call: retrofit2.Call<Recipes>, response: retrofit2.Response<Recipes>) {
                val recipes = response.body()
                if (recipes != null) {
                    val mealRecipe = recipes.meals[0]

                    runOnUiThread {
                        in_tv.text = mealRecipe.strInstructions
                        meal_text.text = mealRecipe.strMeal

                        val thumb = mealRecipe.strMealThumb
                        Picasso.get()
                            .load(thumb)
                            .fit().centerInside()
                            .error(android.R.drawable.stat_notify_error)
                            .into(meal_img)

                        //INGREDIENTS

                        if (!mealRecipe.strIngredient1.isNullOrBlank())
                            ingr_1.text = mealRecipe.strIngredient1
                        else ingr_1.visibility = View.GONE

                        if (!mealRecipe.strIngredient2.isNullOrBlank())
                            ingr_2.text = mealRecipe.strIngredient2
                        else ingr_2.visibility = View.GONE

                        if (!mealRecipe.strIngredient3.isNullOrBlank())
                            ingr_3.text = mealRecipe.strIngredient3
                        else ingr_3.visibility = View.GONE

                        if (!mealRecipe.strIngredient4.isNullOrBlank())
                            ingr_4.text = mealRecipe.strIngredient4
                        else ingr_4.visibility = View.GONE

                        if (!mealRecipe.strIngredient5.isNullOrBlank())
                            ingr_5.text = mealRecipe.strIngredient5
                        else ingr_5.visibility = View.GONE

                        if (!mealRecipe.strIngredient6.isNullOrBlank())
                            ingr_6.text = mealRecipe.strIngredient6
                        else ingr_6.visibility = View.GONE

                        if (!mealRecipe.strIngredient7.isNullOrBlank())
                            ingr_7.text = mealRecipe.strIngredient7
                        else ingr_7.visibility = View.GONE

                        if (!mealRecipe.strIngredient8.isNullOrBlank())
                            ingr_8.text = mealRecipe.strIngredient8
                        else ingr_8.visibility = View.GONE

                        if (!mealRecipe.strIngredient9.isNullOrBlank())
                            ingr_9.text = mealRecipe.strIngredient9
                        else ingr_9.visibility = View.GONE

                        if (!mealRecipe.strIngredient10.isNullOrBlank())
                            ingr_10.text = mealRecipe.strIngredient10
                        else ingr_10.visibility = View.GONE

                        if (!mealRecipe.strIngredient11.isNullOrBlank())
                            ingr_11.text = mealRecipe.strIngredient11
                        else ingr_11.visibility = View.GONE

                        if (!mealRecipe.strIngredient12.isNullOrBlank())
                            ingr_12.text = mealRecipe.strIngredient12
                        else ingr_12.visibility = View.GONE

                        if (!mealRecipe.strIngredient13.isNullOrBlank())
                            ingr_13.text = mealRecipe.strIngredient13
                        else ingr_13.visibility = View.GONE

                        if (!mealRecipe.strIngredient14.isNullOrBlank())
                            ingr_14.text = mealRecipe.strIngredient14
                        else ingr_14.visibility = View.GONE

                        if (!mealRecipe.strIngredient15.isNullOrBlank())
                            ingr_15.text = mealRecipe.strIngredient15
                        else ingr_15.visibility = View.GONE

                        if (!mealRecipe.strIngredient16.isNullOrBlank())
                            ingr_16.text = mealRecipe.strIngredient16
                        else ingr_16.visibility = View.GONE

                        if (!mealRecipe.strIngredient17.isNullOrBlank())
                            ingr_17.text = mealRecipe.strIngredient17
                        else ingr_17.visibility = View.GONE

                        if (!mealRecipe.strIngredient18.isNullOrBlank())
                            ingr_18.text = mealRecipe.strIngredient18
                        else ingr_18.visibility = View.GONE

                        if (!mealRecipe.strIngredient19.isNullOrBlank())
                            ingr_19.text = mealRecipe.strIngredient19
                        else ingr_19.visibility = View.GONE

                        if (!mealRecipe.strIngredient20.isNullOrBlank())
                            ingr_20.text = mealRecipe.strIngredient20
                        else ingr_20.visibility = View.GONE


                        // MEASURES


                        if (!mealRecipe.strMeasure1.isNullOrBlank())
                            am_1.text = mealRecipe.strMeasure1
                        else am_1.visibility = View.GONE

                        if (!mealRecipe.strMeasure2.isNullOrBlank())
                            am_2.text = mealRecipe.strMeasure2
                        else am_2.visibility = View.GONE

                        if (!mealRecipe.strMeasure3.isNullOrBlank())
                            am_3.text = mealRecipe.strMeasure3
                        else am_3.visibility = View.GONE

                        if (!mealRecipe.strMeasure4.isNullOrBlank())
                            am_4.text = mealRecipe.strMeasure4
                        else am_4.visibility = View.GONE

                        if (!mealRecipe.strMeasure5.isNullOrBlank())
                            am_5.text = mealRecipe.strMeasure5
                        else am_5.visibility = View.GONE

                        if (!mealRecipe.strMeasure6.isNullOrBlank())
                            am_6.text = mealRecipe.strMeasure6
                        else am_6.visibility = View.GONE

                        if (!mealRecipe.strMeasure7.isNullOrBlank())
                            am_7.text = mealRecipe.strMeasure7
                        else am_7.visibility = View.GONE

                        if (!mealRecipe.strMeasure8.isNullOrBlank())
                            am_8.text = mealRecipe.strMeasure8
                        else am_8.visibility = View.GONE

                        if (!mealRecipe.strMeasure9.isNullOrBlank())
                            am_9.text = mealRecipe.strMeasure9
                        else am_9.visibility = View.GONE

                        if (!mealRecipe.strMeasure10.isNullOrBlank())
                            am_10.text = mealRecipe.strMeasure10
                        else am_10.visibility = View.GONE

                        if (!mealRecipe.strMeasure11.isNullOrBlank())
                            am_11.text = mealRecipe.strMeasure11
                        else am_11.visibility = View.GONE

                        if (!mealRecipe.strMeasure12.isNullOrBlank())
                            am_12.text = mealRecipe.strMeasure12
                        else am_12.visibility = View.GONE

                        if (!mealRecipe.strMeasure13.isNullOrBlank())
                            am_13.text = mealRecipe.strMeasure13
                        else am_13.visibility = View.GONE

                        if (!mealRecipe.strMeasure14.isNullOrBlank())
                            am_14.text = mealRecipe.strMeasure14
                        else am_14.visibility = View.GONE

                        if (!mealRecipe.strMeasure15.isNullOrBlank())
                            am_15.text = mealRecipe.strMeasure15
                        else am_15.visibility = View.GONE

                        if (!mealRecipe.strMeasure16.isNullOrBlank())
                            am_16.text = mealRecipe.strMeasure16
                        else am_16.visibility = View.GONE

                        if (!mealRecipe.strMeasure17.isNullOrBlank())
                            am_17.text = mealRecipe.strMeasure17
                        else am_17.visibility = View.GONE

                        if (!mealRecipe.strMeasure18.isNullOrBlank())
                            am_18.text = mealRecipe.strMeasure18
                        else am_18.visibility = View.GONE

                        if (!mealRecipe.strMeasure19.isNullOrBlank())
                            am_19.text = mealRecipe.strMeasure19
                        else am_19.visibility = View.GONE

                        if (!mealRecipe.strMeasure20.isNullOrBlank())
                            am_20.text = mealRecipe.strMeasure20
                        else am_20.visibility = View.INVISIBLE


                        yt_btn.setOnClickListener {
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

                        src_btn.setOnClickListener {
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