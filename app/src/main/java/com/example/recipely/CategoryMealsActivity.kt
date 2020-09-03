package com.example.recipely

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipely.Models.MealsRecycler
import com.example.recipely.Adapters.CategoryMealsAdapter
import com.example.recipely.Adapters.CategoryViewHolder
import com.example.recipely.Api.Client
import com.example.recipely.Api.Service
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meals.*

class CategoryMealsActivity: AppCompatActivity() {

    private var service : Service? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)
        back_btn.setOnClickListener {
            onBackPressed()
        }
        service = Client.client.create(Service::class.java)

        category_meals_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        category_meals_text.animation = AnimationUtils.loadAnimation(this, R.anim.scale_big)

        val category = intent.getStringExtra(CategoryViewHolder.NAME_KEY)
        category_meals_text.text = category

        fetchMeals(category)
    }

    private fun fetchMeals(category: String){

        val call = service?.fetchCategoryMeals(category)
        call?.enqueue(object: retrofit2.Callback<MealsRecycler> {

            override fun onResponse(call: retrofit2.Call<MealsRecycler>, response: retrofit2.Response<MealsRecycler>) {
                if (response.isSuccessful) {
                    val category_meals = response.body()
                    runOnUiThread {
                        category_meals_recycler.adapter =
                            category_meals?.let { CategoryMealsAdapter(it) }
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<MealsRecycler>, t: Throwable) {
                println("Loading failed.")
            }
        })
    }
    }
