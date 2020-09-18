package com.example.recipely
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var service : Service? = null
    private val recipes : MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        getCurrentUser()
        recipes.clear()
        service = Client.client.create(Service::class.java)

        discover_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        custom_meals_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        search.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        add_button.setOnClickListener {
            addNewRecipe()
        }

        logout.setOnClickListener {
            logout()
        }
        fetchCategories()
        fetchRecipes()
    }

    override fun onResume() {
        super.onResume()
        recipes.clear()
        getCurrentUser()
        fetchRecipes()
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

    private fun getCurrentUser(){
        val user = mAuth.currentUser
        if (user != null) {
            Log.d("MAIN ACTIVITY USER", user.uid)
        }
    }

    private fun fetchRecipes(){
        val user = mAuth.currentUser
        val titles: MutableList<String> = mutableListOf()
        val ref = user?.uid?.let { firestore.collection("users").document(it).collection("recipes")}
        ref?.get()?.addOnSuccessListener {
        val list: MutableList<DocumentSnapshot> = it.documents
            for(i in list){
                val title = i.getString("title")
                if (title != null) {
                    titles.add(title)
                }
                Log.d("TITLE ", "$titles")
            }
            custom_meals_recycler.adapter = CustomMealsAdapter(titles)
        }
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter = CustomMealsAdapter(titles)))
        itemTouchHelper.attachToRecyclerView(custom_meals_recycler)
    }

    private fun logout(){
        mAuth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}



