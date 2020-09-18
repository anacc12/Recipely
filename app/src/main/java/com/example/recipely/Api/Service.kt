package com.example.recipely.Api

import com.example.recipely.Models.DiscoverRecycler
import com.example.recipely.Models.MealsRecycler
import com.example.recipely.Models.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("categories.php")
    fun fetchCategories(): Call<DiscoverRecycler>

    @GET("filter.php")
    fun fetchCategoryMeals(@Query("c") c: String): Call<MealsRecycler>

    @GET("lookup.php")
    fun fetchMealRecipe(@Query("i") i: String): Call<Recipes>

    @GET("search.php")
    fun fetchMealByName(@Query("s") s: String): Call<Recipes>

}