package com.example.recipely
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipely.Adapters.CategoryAdapter
import com.example.recipely.Adapters.CustomMealsAdapter
import com.example.recipely.Api.Client
import com.example.recipely.Api.Service
import com.example.recipely.Models.DiscoverRecycler
import com.example.recipely.Models.MealRecipes
import com.example.recipely.Models.Recipe
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var service : Service? = null
    val recipes : MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipes.clear()
        service = Client.client.create(Service::class.java)

        discover_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        custom_meals_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val ref = FirebaseDatabase.getInstance().getReference("recipes")

        add_button.setOnClickListener {
            addNewRecipe()
        }

        fetchCategories()
        fetchRecipes(ref)
    }

    override fun onResume() {
        super.onResume()
        recipes.clear()
        val ref = FirebaseDatabase.getInstance().getReference("recipes")
        fetchRecipes(ref)
    }

    private fun fetchCategories(){
        val call = service?.fetchCategories()
        call?.enqueue(object: retrofit2.Callback<DiscoverRecycler> {

            override fun onResponse(call: retrofit2.Call<DiscoverRecycler>, response: retrofit2.Response<DiscoverRecycler>) {
                if (response.isSuccessful) {
                    val categories = response.body()
                    runOnUiThread {
                        discover_recycler.adapter =
                            categories?.let { CategoryAdapter(it) }
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<DiscoverRecycler>, t: Throwable) {
                println("Loading failed.")
            }
        })
    }

    private fun addNewRecipe(){
        val intent = Intent(this, NewRecipeActivity::class.java)
        startActivity(intent)
    }

    private fun fetchRecipes(ref: DatabaseReference){
        val getData = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Cancelled")
            }
            override fun onDataChange(p0: DataSnapshot) {
                for(i in p0.children) {
                    val title = i.child("title").value.toString()
                    val ingredients = i.child("ingredients").value.toString()
                    val instructions = i.child("instructions").value.toString()
                    val key = i.key

                    val recipe = Recipe(
                        key!!,
                        title,
                        ingredients,
                        instructions
                    )

                    if(!(recipes.any{ recipe -> key == recipe.id}))
                    recipes.add(recipe)
                }
                custom_meals_recycler.adapter = CustomMealsAdapter(
                    MealRecipes(
                        recipes
                    )
                )

                val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter = CustomMealsAdapter(MealRecipes(recipes))))
                itemTouchHelper.attachToRecyclerView(custom_meals_recycler)

                }
            }

        ref.addListenerForSingleValueEvent(getData)



    }


}



