package com.example.recipely.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.recipely.Models.Category
import com.example.recipely.CategoryMealsActivity
import com.example.recipely.Models.DiscoverRecycler
import com.example.recipely.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category_meals.view.*
import kotlinx.android.synthetic.main.item_discover.view.*
import kotlinx.android.synthetic.main.item_discover.view.item_card

class CategoryAdapter(private val categories: DiscoverRecycler): RecyclerView.Adapter<CategoryViewHolder>() {

    override fun getItemCount(): Int {
        return categories.categories.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val category = layoutInflater.inflate(R.layout.item_discover, parent, false)
        return CategoryViewHolder(category)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories.categories[position]
        holder.v.category_name.text = category.strCategory
        Picasso.get()
            .load(category.strCategoryThumb)
            .fit().centerInside()
            .error(android.R.drawable.stat_notify_error)
            .into(holder.v.category_thumb)

        holder.v.item_card.animation = AnimationUtils.loadAnimation(holder.v.context,R.anim.scale)

        holder.category=category
    }
}

class CategoryViewHolder(val v: View, var category: Category? = null): RecyclerView.ViewHolder(v){

    companion object{
        const val NAME_KEY = "NAME"
        const val THUMB_KEY = "CATEGORY"
    }

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, CategoryMealsActivity::class.java)

            intent.putExtra(NAME_KEY, category?.strCategory)
            intent.putExtra(THUMB_KEY, category?.strCategoryThumb)

            v.context.startActivity(intent)
        }
    }

}